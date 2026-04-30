package com.vilka.app.identity.auth.service;

import com.vilka.app.identity.auth.dto.*;
import com.vilka.app.identity.user.dto.UserResponse;
import com.vilka.app.identity.user.entity.User;
import com.vilka.app.identity.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public AuthResponse register(RegisterRequest request) {

        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        user = userRepository.save(user);

        return AuthResponse.builder()
                .userId(user.getId())
                .email(user.getEmail())
                .accessToken("dummy-jwt")
                .refreshToken("dummy-refresh")
                .build();
    }

    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        return AuthResponse.builder()
                .userId(user.getId())
                .email(user.getEmail())
                .accessToken("dummy-jwt")
                .refreshToken("dummy-refresh")
                .build();
    }

    public void logout() {}

    public void forgotPassword(ForgotPasswordRequest request) {}

    public void resetPassword(ResetPasswordRequest request) {}

    public UserResponse getCurrentUser() {
        // TODO: extract from security context
        return null;
    }
}
