package com.cooking.dao.repository.recipe;

import com.cooking.dao.model.recipe.Recipe;
import com.cooking.dao.repository.AbstractRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRepository extends AbstractRepository<Recipe> {
}
