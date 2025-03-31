package com.cooking.dao.model.rating;

import com.cooking.dao.model.AbstractEntity;
import com.cooking.dao.model.recipe.Recipe;
import com.cooking.dao.model.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class Rating extends AbstractEntity {

    private Double rating;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;
}
