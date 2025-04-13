package com.cooking.service.common.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public abstract class SpaghettiAiException extends RuntimeException {
    private final String message;

    public SpaghettiAiException(final String message) {
        super(message);
        this.message = message;
    }

    public abstract int code();
}
