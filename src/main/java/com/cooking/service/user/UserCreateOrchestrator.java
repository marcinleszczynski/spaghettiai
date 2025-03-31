package com.cooking.service.user;

import com.cooking.controller.auth.dto.UserRegistrationDto;
import com.cooking.dao.model.user.User;
import com.cooking.dao.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.cooking.dao.model.user.User.Role.USER;

@Service
@RequiredArgsConstructor
public class UserCreateOrchestrator {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void orchestrate(UserRegistrationDto dto) {
        validateUserCredentials(dto);
        createUser(dto);
    }

    private void validateUserCredentials(UserRegistrationDto dto) {
        // TODO - implement validation
        return;
    }

    private void createUser(UserRegistrationDto dto) {
        var user = new User();

        user.setEmail(dto.getEmail());
        user.setPassword(encodePassword(dto.getPassword()));
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setPhoneNumber(dto.getPhoneNumber());
        user.setRole(USER);
        System.out.println("Saving user: " + user);
        userRepository.save(user);
    }

    private String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }
}
