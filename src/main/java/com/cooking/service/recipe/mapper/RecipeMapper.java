package com.cooking.service.recipe.mapper;

import com.cooking.controller.recipe.dto.RecipeResponseDto;
import com.cooking.dao.model.recipe.Recipe;
import org.springframework.stereotype.Service;

@Service
public class RecipeMapper {

    public static RecipeResponseDto map(Recipe recipe) {
        return RecipeResponseDto.builder()
                .name(recipe.getName())
                .content(recipe.getContent())
                .build();
    }
}
