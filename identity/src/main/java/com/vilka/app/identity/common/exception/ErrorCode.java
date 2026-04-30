package com.vilka.app.identity.common.exception;

import org.springframework.http.HttpStatus;

public enum ErrorCode {

    USER_NOT_FOUND("USER_NOT_FOUND", HttpStatus.NOT_FOUND, "User not found"),
    PROFILE_NOT_FOUND("PROFILE_NOT_FOUND", HttpStatus.NOT_FOUND, "Profile not found"),
    EMAIL_ALREADY_EXIST("EMAIL_ALREADY_EXIST", HttpStatus.CONFLICT, "Email already exists"),
    VALIDATION_ERROR("VALIDATION_ERROR", HttpStatus.BAD_REQUEST, "Validation failed"),
    INTERNAL_ERROR("INTERNAL_ERROR", HttpStatus.INTERNAL_SERVER_ERROR, "Something went wrong");

    private final String code;
    private final HttpStatus status;
    private final String message;

    ErrorCode(String code, HttpStatus status, String message) {
        this.code = code;
        this.status = status;
        this.message = message;
    }

    public String getCode() { return code; }
    public HttpStatus getStatus() { return status; }
    public String getMessage() { return message; }
}
