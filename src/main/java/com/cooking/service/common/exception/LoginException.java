package com.cooking.service.common.exception;

public class LoginException extends SpaghettiAiException {
    public LoginException(String message) {
        super(message);
    }

    @Override
    public int code() {
        return 403;
    }
}
