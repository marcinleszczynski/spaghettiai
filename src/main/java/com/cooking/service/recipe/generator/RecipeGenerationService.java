package com.cooking.service.recipe.generator;

import com.cooking.controller.recipe.dto.RecipeDescriptionDto;
import com.cooking.controller.recipe.dto.RecipeDto;
import com.cooking.controller.recipe.dto.RecipeGenerationRequestDto;
import com.cooking.dao.model.recipe.Recipe;
import com.cooking.dao.repository.recipe.RecipeRepository;
import com.cooking.service.integration.openai.OpenAiService;
import com.cooking.service.recipe.factory.RecipeFactory;
import com.cooking.service.recipe.generator.dto.RecipeAiResponseDto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.cooking.service.recipe.mapper.RecipeMapper.map;
import static com.cooking.service.utils.StringUtils.*;
import static com.cooking.controller.recipe.dto.RecipeGenerationRequestDto.IngredientDto;

@Slf4j
@Service
@RequiredArgsConstructor
public class RecipeGenerationService {

    private final ObjectMapper objectMapper;
    private final OpenAiService openAiService;
    private final RecipeRepository recipeRepository;
    private final RecipeFactory recipeFactory;

    public RecipeDto generateRecipe(RecipeDescriptionDto dto) {

        var ingredientAnalysisRequest = prepareIngredientAnalysisRequest(dto);
        var ingredients = executeIngredientsAnalysisRequest(ingredientAnalysisRequest);

        var recipeGenerationRequest = prepareRecipeGenerationRequest(ingredients);
        var generatedRecipe = executeRecipeGenerationRequest(recipeGenerationRequest);
        recipeRepository.save(generatedRecipe);

        return map(generatedRecipe);
    }

    private String prepareIngredientAnalysisRequest(RecipeDescriptionDto dto) {

        var promptTemplate = new PromptTemplate(new ClassPathResource("templates/prompts/analyse_ingredients.st"));
        promptTemplate.add("description", dto.getDescription());

        return promptTemplate.render();
    }

    @SneakyThrows
    private RecipeGenerationRequestDto executeIngredientsAnalysisRequest(String request) {
        var response = unwrapListFromWindowMarkdown(openAiService.process(request));
        var ingredients = objectMapper.readValue(response, new TypeReference<List<IngredientDto>>() {
        });

        return RecipeGenerationRequestDto.builder()
                .ingredients(ingredients)
                .build();
    }

    @SneakyThrows
    private String prepareRecipeGenerationRequest(RecipeGenerationRequestDto dto) {
        var promptTemplate = new PromptTemplate(new ClassPathResource("templates/prompts/create_recipe_prompt.st"));
        promptTemplate.add("request", objectMapper.writeValueAsString(dto.getIngredients()));

        return promptTemplate.render();
    }

    @SneakyThrows
    private Recipe executeRecipeGenerationRequest(String request) {
        var response = unwrapJSONFromWindowMarkdown(openAiService.process(request));
        var aiResponse = objectMapper.readValue(response, RecipeAiResponseDto.class);

        return recipeFactory.from(aiResponse);
    }
}
