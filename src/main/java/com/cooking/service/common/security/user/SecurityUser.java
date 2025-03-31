package com.cooking.service.common.security.user;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper=false)
public class SecurityUser extends User {

    private final String userId;
    private final String email;
    private final String fullName;

    public SecurityUser(String username, String password, Collection<? extends GrantedAuthority> authorities, String userId, String email, String fullName) {
        super(username, password, authorities);
        this.userId = userId;
        this.email = email;
        this.fullName = fullName;
    }
}
