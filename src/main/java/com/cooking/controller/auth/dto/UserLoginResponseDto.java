package com.cooking.controller.auth.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserLoginResponseDto {
    private String token;
}
