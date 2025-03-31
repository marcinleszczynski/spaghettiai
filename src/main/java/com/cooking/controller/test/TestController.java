package com.cooking.controller.test;

import com.cooking.service.integration.openai.OpenAiService;
import jakarta.annotation.security.RolesAllowed;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.cooking.service.common.security.SecurityConstants.ADMIN;
import static com.cooking.service.common.security.SecurityConstants.USER;

@Slf4j
@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {

    private final OpenAiService openAiService;

    @RolesAllowed({USER, ADMIN})
    @GetMapping
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("Hello World");
    }

    @RolesAllowed({ADMIN})
    @GetMapping("/admin")
    public ResponseEntity<String> testAdmin() {
        return ResponseEntity.ok("Hello World to Admin!");
    }

    @PostMapping("/llm")
    public ResponseEntity<String> testPost() {
        log.info("Received a test request to check if LLM is working");
        return ResponseEntity.ok(openAiService.process("Hello, tell me an interesting fact about google please."));
    }
}