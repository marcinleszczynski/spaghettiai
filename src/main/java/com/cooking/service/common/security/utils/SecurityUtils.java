package com.cooking.service.common.security.utils;

import com.cooking.service.common.security.user.SecurityUser;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SecurityUtils {

    public static UUID getAuthenticatedUserId() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof SecurityUser user) {
            return UUID.fromString(user.getUserId());
        }
        throw new UsernameNotFoundException("User is not authenticated");
    }

    public static String getAuthenticatedUserEmail() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof SecurityUser user) {
            return user.getEmail();
        }
        throw new UsernameNotFoundException("User is not authenticated");
    }
}
