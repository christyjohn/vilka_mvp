package com.vilka.app.vendor.common.exception;

import org.springframework.http.HttpStatus;

public enum ErrorCode {

    VENDOR_NOT_FOUND("VENDOR_NOT_FOUND", HttpStatus.NOT_FOUND, "Vendor not found"),
    VENDOR_ALREADY_EXIST("VENDOR_ALREADY_EXIST", HttpStatus.CONFLICT, "Vendor already exist"),
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
