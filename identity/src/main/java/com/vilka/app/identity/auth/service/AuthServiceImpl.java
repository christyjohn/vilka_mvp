package com.vilka.app.identity.auth.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    // TODO - fix the classnames
    /*
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
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

    @Override
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

    @Override
    public void logout() {}

    @Override
    public void forgotPassword(ForgotPasswordRequest request) {}

    @Override
    public void resetPassword(ResetPasswordRequest request) {}

    @Override
    public UserResponse getCurrentUser() {
        // TODO: extract from security context
        return null;
    }
     */
}
