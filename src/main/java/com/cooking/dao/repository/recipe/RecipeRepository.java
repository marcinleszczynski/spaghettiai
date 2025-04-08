package com.cooking.dao.repository.recipe;

import com.cooking.dao.model.recipe.Recipe;
import com.cooking.dao.repository.AbstractRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;
import java.util.UUID;

@Repository
public interface RecipeRepository extends AbstractRepository<Recipe> {

    List<Recipe> findAllByUser_Id(UUID id);

    List<Recipe> findByUser_IdOrderByModificationTimestampDesc(UUID userId, PageRequest pageRequest);
}
