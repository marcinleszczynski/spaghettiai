package com.cooking.controller.recipe.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RecipeResponseDto {
    private String name;
    private String content;
}
