package com.cooking.service.integration.mail;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
public class EmailService {

    @Value("${mail.sender.email}")
    private String senderEmail;

    @Value("${mail.sender.password}")
    private String senderPassword;

    @SneakyThrows
    public void sendEmail(String recipient, String text) {
        var session = createSession();
        var message = createMessage(session, recipient, text);


        var transport = session.getTransport("smtp");
        transport.connect("smtp.gmail.com", senderEmail, senderPassword);
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
    }

    private Session createSession() {
        var properties = new Properties();
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        return Session.getDefaultInstance(properties, null);
    }

    @SneakyThrows
    private MimeMessage createMessage(Session session, String to, String text) {
        var result = new MimeMessage(session);

        result.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
        result.setSubject("SpaghettiAI activation account");

        var multipart = new MimeMultipart();
        var bodyPart = new MimeBodyPart();

        bodyPart.setContent(text, "text/html; charset=utf-8");
        multipart.addBodyPart(bodyPart);
        result.setContent(multipart);

        return result;
    }
}
