package com.cooking.service.user;

import com.cooking.dao.model.user.User;
import com.cooking.dao.repository.user.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.cooking.service.utils.SecurityUtils.getAuthenticatedUserId;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User findAuthenticatedUser() {
        return userRepository
                .findById(getAuthenticatedUserId())
                .orElseThrow(() -> new EntityNotFoundException("User with id " + getAuthenticatedUserId() + " not found"));
    }
}
