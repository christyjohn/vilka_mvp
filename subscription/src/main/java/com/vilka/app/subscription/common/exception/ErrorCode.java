package com.vilka.app.subscription.common.exception;

import org.springframework.http.HttpStatus;

public enum ErrorCode {

    USER_NOT_FOUND("USER_NOT_FOUND", HttpStatus.NOT_FOUND, "User not found"),
    INVALID_CREDENTIALS("INVALID_CREDENTIALS", HttpStatus.UNAUTHORIZED, "Invalid credentials"),
    VENDOR_NOT_FOUND("VENDOR_NOT_FOUND", HttpStatus.NOT_FOUND, "Vendor not found"),
    OFFERING_NOT_FOUND("OFFERING_NOT_FOUND", HttpStatus.NOT_FOUND, "Offering not found"),
    NOT_SUBSCRIBED("NOT_SUBSCRIBED", HttpStatus.NOT_FOUND, "Subscription not found"),
    ALREADY_SUBSCRIBED("ALREADY_SUBSCRIBED", HttpStatus.CONFLICT, "User already subscribed."),
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
