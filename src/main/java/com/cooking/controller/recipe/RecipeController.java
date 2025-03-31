package com.cooking.controller.recipe;

import com.cooking.controller.recipe.dto.RecipeGenerationRequestDto;
import com.cooking.controller.recipe.dto.RecipeGenerationResponseDto;
import com.cooking.service.recipe.generator.RecipeGenerationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.cooking.service.utils.SecurityUtils.getAuthenticatedUserEmail;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/recipe")
public class RecipeController {

    private final RecipeGenerationService recipeGeneratorService;

    @PostMapping("/generate")
    public ResponseEntity<RecipeGenerationResponseDto> generateRecipe(@RequestBody RecipeGenerationRequestDto dto) {
        log.info("Received a request to generate recipe for user: {}", getAuthenticatedUserEmail());
        return ResponseEntity.ok(recipeGeneratorService.generateRecipe(dto));
    }
}
