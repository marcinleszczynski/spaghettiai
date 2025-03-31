package com.cooking.dao.model.user;

import com.cooking.dao.model.AbstractEntity;
import com.cooking.dao.model.recipe.Recipe;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.cooking.service.common.security.SecurityConstants.ROLE_ADMIN;
import static com.cooking.service.common.security.SecurityConstants.ROLE_USER;

@Data
@Entity
@Table(name = "\"user\"")
@EqualsAndHashCode(callSuper = true)
public class User extends AbstractEntity {

    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    public List<Recipe> recipes;

    @Getter
    @RequiredArgsConstructor
    public enum Role {
        USER(ROLE_USER),
        ADMIN(ROLE_ADMIN);
        private final String value;
    }
}
