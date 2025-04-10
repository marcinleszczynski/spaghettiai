package com.cooking.service.common.email;

import jakarta.mail.NoSuchProviderException;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class SMTPConfig {

    @Bean
    public Session session() {
        var properties = new Properties();
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        return Session.getDefaultInstance(properties, null);
    }

    @Bean
    public Transport transport(Session session) throws NoSuchProviderException {
        return session.getTransport("smtp");
    }
}
