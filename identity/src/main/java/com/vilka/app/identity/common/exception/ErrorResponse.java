package com.vilka.app.identity.common.exception;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ErrorResponse {
    private LocalDateTime timestamp;
    private String code;
    private String message;
    private Object details;

    public ErrorResponse(String code, String message, Object details) {
        this.timestamp = LocalDateTime.now();
        this.code = code;
        this.message = message;
        this.details = details;
    }
}
