package com.cooking.controller.auth.dto;

import lombok.Data;

@Data
public class UserRegistrationDto {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String phoneNumber;
}
