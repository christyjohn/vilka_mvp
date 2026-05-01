package com.vilka.app.identity.auth.service;

import com.vilka.app.identity.auth.dto.*;
import com.vilka.app.identity.auth.security.jwt.JwtUtil;
import com.vilka.app.identity.common.exception.ApiException;
import com.vilka.app.identity.common.exception.ErrorCode;
import com.vilka.app.identity.user.entity.User;
import com.vilka.app.identity.user.mapper.UserMapper;
import com.vilka.app.identity.user.repository.UserRepository;
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

    @Transactional
    public AuthResponse register(RegisterRequest request) {

        if (userRepository.existsByUsername(request.getUsername())) {
            throw new ApiException(ErrorCode.USERNAME_ALREADY_EXIST);
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        userRepository.save(user);


        List<String> roles = List.of("USER");
        List<String> permissions = List.of("CREATE_SERVICE");

        String token = jwtUtil.generateToken(
                user.getId(),
                user.getUsername(),
                roles,
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

        List<String> roles = List.of("USER");
        List<String> permissions = List.of("CREATE_SERVICE");

        String token = jwtUtil.generateToken(
                user.getId(),
                user.getUsername(),
                roles,
                permissions);

        return token;
    }

    public void logout() {}

    public void forgotPassword(ForgotPasswordRequest request) {}

    public void resetPassword(ResetPasswordRequest request) {}

}
