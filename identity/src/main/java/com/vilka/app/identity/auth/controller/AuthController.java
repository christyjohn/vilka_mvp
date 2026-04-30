package com.vilka.app.identity.auth.controller;

import com.vilka.app.identity.auth.dto.*;
import com.vilka.app.identity.auth.service.AuthService;
import com.vilka.app.identity.common.exception.ApiException;
import com.vilka.app.identity.common.exception.ErrorCode;
import com.vilka.app.identity.user.dto.UserResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public AuthResponse register(@Valid @RequestBody RegisterRequest request) {
        return authService.register(request);
    }

    @PostMapping("/login")
    public AuthResponse login(@Valid @RequestBody LoginRequest request) {
        return authService.login(request);
    }

    @PostMapping("/logout")
    public void logout() {
        throw new ApiException(ErrorCode.NOT_IMPLEMENTED);
    }

    @PostMapping("/forgot-password")
    public void forgotPassword(@RequestBody ForgotPasswordRequest request) {
        throw new ApiException(ErrorCode.NOT_IMPLEMENTED);
    }

    @PostMapping("/reset-password")
    public void resetPassword(@RequestBody ResetPasswordRequest request) {
        throw new ApiException(ErrorCode.NOT_IMPLEMENTED);
    }

    @GetMapping("/me")
    public UserResponse me() {
        return authService.getCurrentUser();
    }
}
