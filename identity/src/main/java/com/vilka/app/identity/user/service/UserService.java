package com.vilka.app.identity.user.service;

import com.vilka.app.identity.common.exception.ApiException;
import com.vilka.app.identity.common.exception.ErrorCode;
import com.vilka.app.identity.user.dto.UserExistsResponse;
import com.vilka.app.identity.user.dto.UserResponse;
import com.vilka.app.identity.user.entity.User;
import com.vilka.app.identity.user.mapper.UserMapper;
import com.vilka.app.identity.user.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
    }

    public UserExistsResponse checkUserExists(Long userId) {
        log.debug("Checking if user exists with id={}", userId);
        boolean exists = userRepository.existsById(userId);
        return new UserExistsResponse(exists);
    }

    public UserResponse getUser(Long id) {
        log.debug("Fetching user with id={}", id);

        User user = userRepository.findById(id)

                .orElseThrow(() -> {
                    log.warn("User not found with id={}", id);
                    return new ApiException(ErrorCode.USER_NOT_FOUND);
                });

        return UserMapper.toResponse(user);
    }
}
