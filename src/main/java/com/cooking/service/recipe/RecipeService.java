package com.cooking.service.recipe;

import com.cooking.controller.recipe.dto.RecipeDto;
import com.cooking.dao.repository.recipe.RecipeRepository;
import com.cooking.service.recipe.mapper.RecipeMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static com.cooking.service.recipe.mapper.RecipeMapper.map;

@Service
@RequiredArgsConstructor
public class RecipeService {

    private final RecipeRepository recipeRepository;

    public RecipeDto findRecipeById(UUID id) {
        var result = recipeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Recipe with id " + id + " not found"));

        return map(result);
    }

    public List<RecipeDto> findAllRecipesForUser(UUID userId) {
        var result = recipeRepository.findAllByUser_Id(userId);

        return result
                .stream()
                .map(RecipeMapper::map)
                .toList();
    }
}
