package com.cooking.service.recipe.generator.dto;

import lombok.Data;

@Data
public class RecipeAiResponseDto {
    private String name;
    private String content;
    private String explanation;
}
