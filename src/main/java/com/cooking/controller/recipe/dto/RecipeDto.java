package com.cooking.controller.recipe.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
@Builder
public class RecipeDto {
    private UUID id;
    private String name;
    private String content;
    private String explanation;
    private Date creationTimestamp;
}
