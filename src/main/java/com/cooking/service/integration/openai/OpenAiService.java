package com.cooking.service.integration.openai;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class OpenAiService {

    private final ChatClient chatClient;

    public String process(String message) {

        return chatClient
                .prompt(message)
                .call()
                .content();
    }
}
