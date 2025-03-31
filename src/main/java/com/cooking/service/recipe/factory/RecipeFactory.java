package com.cooking.service.recipe.factory;

import com.cooking.dao.model.recipe.Recipe;
import com.cooking.service.recipe.generator.dto.RecipeAiResponseDto;
import com.cooking.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RecipeFactory {

    private final UserService userService;

    public Recipe from(RecipeAiResponseDto dto) {
        var result = new Recipe();

        result.setName(dto.getName());
        result.setContent(dto.getContent());
        result.setPublic(false);
        result.setUser(userService.findAuthenticatedUser());
        result.setExplanation(dto.getExplanation());

        return result;
    }
}
