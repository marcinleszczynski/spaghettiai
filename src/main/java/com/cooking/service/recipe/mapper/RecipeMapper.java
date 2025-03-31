package com.cooking.service.recipe.mapper;

import com.cooking.controller.recipe.dto.RecipeGenerationResponseDto;
import com.cooking.dao.model.recipe.Recipe;
import org.springframework.stereotype.Service;

@Service
public class RecipeMapper {

    public static RecipeGenerationResponseDto map(Recipe recipe) {
        return RecipeGenerationResponseDto.builder()
                .name(recipe.getName())
                .content(recipe.getContent())
                .explanation(recipe.getExplanation())
                .build();
    }
}
