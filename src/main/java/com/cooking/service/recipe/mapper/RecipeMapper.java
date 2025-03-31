package com.cooking.service.recipe.mapper;

import com.cooking.controller.recipe.dto.RecipeDto;
import com.cooking.dao.model.recipe.Recipe;
import org.springframework.stereotype.Service;

@Service
public class RecipeMapper {

    public static RecipeDto map(Recipe recipe) {
        return RecipeDto.builder()
                .id(recipe.getId())
                .name(recipe.getName())
                .content(recipe.getContent())
                .explanation(recipe.getExplanation())
                .creationTimestamp(recipe.getCreationTimestamp())
                .build();
    }
}
