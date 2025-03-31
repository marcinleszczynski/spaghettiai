package com.cooking.dao.repository.recipe;

import com.cooking.dao.model.recipe.Recipe;
import com.cooking.dao.repository.AbstractRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RecipeRepository extends AbstractRepository<Recipe> {

    List<Recipe> findAllByUser_Id(UUID id);
}
