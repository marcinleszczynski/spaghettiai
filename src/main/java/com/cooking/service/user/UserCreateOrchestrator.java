package com.cooking.service.user;

import com.cooking.controller.auth.dto.UserRegistrationDto;
import com.cooking.dao.model.user.User;
import com.cooking.dao.repository.user.UserRepository;
import com.cooking.service.integration.mail.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.cooking.dao.model.user.User.Role.USER;

@Service
@RequiredArgsConstructor
public class UserCreateOrchestrator {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;

    @Value("${app.frontend-url}")
    private String frontendUrl;

    public void orchestrate(UserRegistrationDto dto) {
        validateUserCredentials(dto);
        var user = createUser(dto);
        sendActivationEmail(user);
    }

    private void validateUserCredentials(UserRegistrationDto dto) {
        // TODO - implement validation
        return;
    }

    private User createUser(UserRegistrationDto dto) {
        var user = new User();

        user.setEmail(dto.getEmail());
        user.setPassword(encodePassword(dto.getPassword()));
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setPhoneNumber(dto.getPhoneNumber());
        user.setRole(USER);
        user.setActivated(false);
        user.setActivationCode(UUID.randomUUID());
        System.out.println("Saving user: " + user);
        return userRepository.save(user);
    }

    private String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    private void sendActivationEmail(User user) {
        var accountActivationTemplate = new PromptTemplate(new ClassPathResource("templates/email/account-activation.st"));
        accountActivationTemplate.add("name", user.getFirstName());
        accountActivationTemplate.add("activationUrl", frontendUrl + "/auth/activate?code=" + user.getActivationCode());
        var message = accountActivationTemplate.render();
        emailService.sendEmail(user.getEmail(), message);
    }
}
