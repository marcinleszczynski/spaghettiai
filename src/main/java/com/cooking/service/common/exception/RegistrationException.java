package com.cooking.service.common.exception;

public class RegistrationException extends SpaghettiAiException {
    public RegistrationException(String message) {
        super(message);
    }

    @Override
    public int code() {
        return 500;
    }
}
