package com.cooking.controller.recipe.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RecipeGenerationResponseDto {
    private String name;
    private String content;
    private String explanation;
}
