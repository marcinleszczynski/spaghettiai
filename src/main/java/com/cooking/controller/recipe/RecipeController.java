package com.cooking.controller.recipe;

import com.cooking.controller.recipe.dto.RecipeDescriptionDto;
import com.cooking.controller.recipe.dto.RecipeDto;
import com.cooking.controller.recipe.dto.RecipeGenerationRequestDto;
import com.cooking.service.recipe.RecipeService;
import com.cooking.service.recipe.generator.RecipeGenerationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static com.cooking.service.common.security.utils.SecurityUtils.getAuthenticatedUserEmail;
import static com.cooking.service.common.security.utils.SecurityUtils.getAuthenticatedUserId;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/recipe")
public class RecipeController {

    private final RecipeGenerationService recipeGeneratorService;
    private final RecipeService recipeService;

    @PostMapping("/generate")
    public ResponseEntity<RecipeDto> generateRecipe(@RequestBody RecipeDescriptionDto dto) {
        log.info("Received a request to generate recipe for user: {}", getAuthenticatedUserEmail());
        return ResponseEntity.ok(recipeGeneratorService.generateRecipe(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecipeDto> getRecipe(@PathVariable UUID id) {
        log.info("Received a request to get recipe with id: {}", id);
        return ResponseEntity.ok(recipeService.findRecipeById(id));
    }

    @GetMapping("/recent")
    public ResponseEntity<List<RecipeDto>> getRecentRecipes(@RequestParam("limit") int limit) {
        log.info("Received a request to get recent recipes for user: {}", getAuthenticatedUserEmail());
        return ResponseEntity.ok(recipeService.findRecentRecipes(getAuthenticatedUserId(), limit));
    }

    @GetMapping
    public ResponseEntity<List<RecipeDto>> getAllRecipesForUser() {
        log.info("Received a request to get all recipes for user: {}", getAuthenticatedUserEmail());
        return ResponseEntity.ok(recipeService.findAllRecipesForUser(getAuthenticatedUserId()));
    }
}
