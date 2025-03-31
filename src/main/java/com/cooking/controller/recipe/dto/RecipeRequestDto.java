package com.cooking.controller.recipe.dto;

import lombok.Data;

import java.util.List;

@Data
public class RecipeRequestDto {

    private List<IngredientDto> ingredients;

    @Data
    public static class IngredientDto {
        private String name;
        private String quantity;
    }
}
