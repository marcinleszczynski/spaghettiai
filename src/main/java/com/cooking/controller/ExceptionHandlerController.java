package com.cooking.controller;

import com.cooking.controller.exception.SpaghettiAiErrorDto;
import com.cooking.service.common.exception.SpaghettiAiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(SpaghettiAiException.class)
    public ResponseEntity<SpaghettiAiErrorDto> handleSpaghettiAiException(SpaghettiAiException e) {
        log.info("Handling SpaghettiAiException: ({}, {})", e.code(), e.getMessage());
        return ResponseEntity.status(e.code()).body(create(e));
    }

    private SpaghettiAiErrorDto create(Exception e) {
        return new SpaghettiAiErrorDto(e.getMessage());
    }
}
