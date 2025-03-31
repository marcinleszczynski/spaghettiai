package com.cooking.controller.auth;

import com.cooking.controller.auth.dto.UserLoginRequestDto;
import com.cooking.controller.auth.dto.UserLoginResponseDto;
import com.cooking.controller.auth.dto.UserRegistrationDto;
import com.cooking.service.auth.AuthService;
import com.cooking.service.user.UserCreateOrchestrator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserCreateOrchestrator userCreateOrchestrator;
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody UserRegistrationDto dto) {
        log.info("Received request to register a user: {}", dto.getEmail());
        userCreateOrchestrator.orchestrate(dto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<UserLoginResponseDto> login(@RequestBody UserLoginRequestDto dto) {
        log.info("Received a request to login a user: {}", dto.getEmail());
        return ResponseEntity.ok(authService.login(dto));
    }
}
