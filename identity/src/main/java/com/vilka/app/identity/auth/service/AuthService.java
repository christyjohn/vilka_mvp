package com.vilka.app.identity.auth.service;

import com.vilka.app.identity.auth.dto.*;
import com.vilka.app.identity.auth.security.jwt.JwtProperties;
import com.vilka.app.identity.auth.security.jwt.JwtUtil;
import com.vilka.app.identity.common.exception.ApiException;
import com.vilka.app.identity.common.exception.ErrorCode;
import com.vilka.app.identity.user.entity.Role;
import com.vilka.app.identity.user.entity.User;
import com.vilka.app.identity.user.mapper.UserMapper;
import com.vilka.app.identity.user.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final JwtProperties jwtProperties;

    @PostConstruct
    public void debugSecret() {
        System.out.println("🔥 JWT SECRET = " + jwtProperties.getSecret());
    }

    @Transactional
    public AuthResponse register(RegisterRequest request) {

        if (userRepository.existsByUsername(request.getUsername())) {
            throw new ApiException(ErrorCode.USERNAME_ALREADY_EXIST);
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setRole(Role.USER);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        userRepository.save(user);

        List<String> permissions = getPermissions(Role.USER);

        String token = jwtUtil.generateToken(
                user.getId(),
                user.getUsername(),
                List.of(user.getRole()),
                permissions);

        return AuthResponse.builder()
                .accessToken(token)
                .userId(user.getId())
                .username(user.getUsername())
                .build();
    }

    public String login(LoginRequest request) {

        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> {
                    return new ApiException(ErrorCode.INVALID_CREDENTIALS);
                });

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new ApiException(ErrorCode.INVALID_CREDENTIALS);
        }

        Role role = user.getRole();
        List<String> permissions = getPermissions(role);

        String token = jwtUtil.generateToken(
                user.getId(),
                user.getUsername(),
                List.of(user.getRole()),
                permissions);

        return token;
    }

    private List<String> getPermissions(Role role) {
        return switch (role) {
            case USER -> List.of("VENDOR_APPLY");
            case ADMIN -> List.of("VENDOR_APPROVE", "VENDOR_REJECT");
            case VENDOR -> List.of("CREATE_SERVICE");
        };
    }

    public void logout() {}

    public void forgotPassword(ForgotPasswordRequest request) {}

    public void resetPassword(ResetPasswordRequest request) {}

}
