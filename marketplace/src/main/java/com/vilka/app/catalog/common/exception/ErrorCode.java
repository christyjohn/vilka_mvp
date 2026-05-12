package com.vilka.app.catalog.common.exception;

import org.springframework.http.HttpStatus;

public enum ErrorCode {

    OFFERING_NOT_FOUND("OFFERING_NOT_FOUND", HttpStatus.NOT_FOUND, "Offering not found"),
    VENDOR_NOT_FOUND("VENDOR_NOT_FOUND", HttpStatus.NOT_FOUND, "Vendor not found"),
    EMAIL_ALREADY_EXIST("EMAIL_ALREADY_EXIST", HttpStatus.CONFLICT, "Email already exists"),
    VALIDATION_ERROR("VALIDATION_ERROR", HttpStatus.BAD_REQUEST, "Validation failed"),
    INVALID_CREDENTIALS("INVALID_CREDENTIALS", HttpStatus.UNAUTHORIZED, "Invalid credentials"),
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
