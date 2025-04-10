package com.cooking.service.integration.mail;

import org.junit.jupiter.api.Test;

public class EmailServiceTest {

    @Test
    public void test() {
        var emailService = new EmailService();
        emailService.sendEmail("venkman2003@gmail.com", "hello");
    }

}