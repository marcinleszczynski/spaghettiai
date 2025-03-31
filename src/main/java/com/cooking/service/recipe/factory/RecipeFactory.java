package com.cooking.service.recipe.factory;

import com.cooking.dao.model.recipe.Recipe;
import com.cooking.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RecipeFactory {

    private final UserService userService;

    public Recipe create(String name, String content) {
        var result = new Recipe();

        result.setName(name);
        result.setContent(content);
        result.setPublic(false);
        result.setUser(userService.findAuthenticatedUser());

        return result;
    }
}
