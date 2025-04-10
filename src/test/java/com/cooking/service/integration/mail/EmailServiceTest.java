package com.cooking.service.integration.mail;

import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.bean.override.mockito.MockitoSpyBean;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;

@ExtendWith(MockitoExtension.class)
public class EmailServiceTest {

    @MockitoSpyBean
    private Session session;

    @Mock
    private Transport transport;

    @InjectMocks
    private EmailService emailService;

    @Test
    public void test() throws MessagingException {

        var message = "hello";
        var recipient = "venkman2003@gmail.com";

        doNothing().when(transport).sendMessage(any(), any());

        emailService.sendEmail(message, recipient);
    }

}