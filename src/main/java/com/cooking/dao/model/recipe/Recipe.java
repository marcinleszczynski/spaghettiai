package com.cooking.dao.model.recipe;

import com.cooking.dao.model.AbstractEntity;
import com.cooking.dao.model.user.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class Recipe extends AbstractEntity {

    private String name;
    private String content;

    @Column(name = "public")
    private boolean isPublic;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String explanation;
}
