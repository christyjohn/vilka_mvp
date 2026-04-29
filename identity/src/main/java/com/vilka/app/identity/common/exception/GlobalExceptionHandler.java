package com.vilka.app.identity.common.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<?> handleApiException(ApiException ex) {
        ErrorCode code = ex.getCode();

        return ResponseEntity
                .status(code.getStatus())
                .body(Map.of(
                        "error", code.name(),
                        "message", code.getMessage()
                ));
    }
}
