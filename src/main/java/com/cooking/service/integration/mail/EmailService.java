package com.cooking.service.integration.mail;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    @Value("${mail.sender.email}")
    private String senderEmail;

    @Value("${mail.sender.password}")
    private String senderPassword;

    private final Session session;
    private final Transport transport;

    @SneakyThrows
    public void sendEmail(String recipient, String subject, String text) {
        var message = createMessage(session, recipient, subject, text);

        transport.connect("smtp.gmail.com", senderEmail, senderPassword);
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
    }

    @SneakyThrows
    private MimeMessage createMessage(Session session, String to, String subject, String text) {
        var result = new MimeMessage(session);

        result.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
        result.setSubject(subject);

        var multipart = new MimeMultipart();
        var bodyPart = new MimeBodyPart();

        bodyPart.setContent(text, "text/html; charset=utf-8");
        multipart.addBodyPart(bodyPart);
        result.setContent(multipart);

        return result;
    }
}
