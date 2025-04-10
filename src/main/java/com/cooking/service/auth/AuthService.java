package com.cooking.service.auth;

import com.cooking.controller.auth.dto.UserLoginRequestDto;
import com.cooking.controller.auth.dto.UserLoginResponseDto;
import com.cooking.dao.repository.user.UserRepository;
import com.cooking.service.common.security.jwt.JwtService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public UserLoginResponseDto login(UserLoginRequestDto dto) {
        var email = dto.getEmail();
        var password = dto.getPassword();

        var user = userRepository
                .findByEmailAndActivatedTrue(email)
                .orElseThrow(() -> new UsernameNotFoundException("User with email " + email + " not found"));

        if (comparePasswordHashes(password, user.getPassword())) {
            return UserLoginResponseDto.builder()
                    .token(jwtService.createToken(user))
                    .build();
        }
        throw new UsernameNotFoundException("Wrong password");
    }

    public void activate(UUID activationCode) {
        var user = userRepository
                .findByActivationCode(activationCode)
                .orElseThrow(() -> new EntityNotFoundException("User with activation code " + activationCode + " not found"));
        user.setActivated(true);
        user.setActivationCode(null);
        userRepository.save(user);
    }

    private boolean comparePasswordHashes(String password, String hash) {
        return passwordEncoder.matches(password, hash);
    }
}
