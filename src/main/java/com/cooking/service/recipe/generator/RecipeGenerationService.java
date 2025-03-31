package com.cooking.service.recipe.generator;

import com.cooking.controller.recipe.dto.RecipeRequestDto;
import com.cooking.controller.recipe.dto.RecipeResponseDto;
import com.cooking.dao.model.recipe.Recipe;
import com.cooking.dao.repository.recipe.RecipeRepository;
import com.cooking.service.integration.openai.OpenAiService;
import com.cooking.service.recipe.factory.RecipeFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import static com.cooking.service.recipe.mapper.RecipeMapper.map;
import static com.cooking.service.utils.StringUtils.unwrapFromWindowMarkdown;

@Service
@RequiredArgsConstructor
public class RecipeGenerationService {

    private final ObjectMapper objectMapper;
    private final OpenAiService openAiService;
    private final RecipeRepository recipeRepository;
    private final RecipeFactory recipeFactory;

    public RecipeResponseDto generateRecipe(RecipeRequestDto dto) {

        var recipeGenerationRequest = prepareRecipeGenerationRequest(dto);
        var generatedRecipe = executeRecipeGenerationRequest(recipeGenerationRequest);
        recipeRepository.save(generatedRecipe);

        return map(generatedRecipe);
    }

    @SneakyThrows
    private String prepareRecipeGenerationRequest(RecipeRequestDto dto) {
        var promptTemplate = new PromptTemplate(new ClassPathResource("prompts/create_recipe_prompt.st"));
        promptTemplate.add("request", objectMapper.writeValueAsString(dto.getIngredients()));

        return promptTemplate.render();
    }

    @SneakyThrows
    private Recipe executeRecipeGenerationRequest(String request) {
        var response = unwrapFromWindowMarkdown(openAiService.process(request));

        var json = objectMapper.readTree(response);
        var name = json.get("name").asText();
        var text = json.get("text").asText();

        return recipeFactory.create(name, text);
    }
}
