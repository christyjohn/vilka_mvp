package com.vilka.app.identity.auth.security.service;

import com.vilka.app.identity.common.exception.ApiException;
import com.vilka.app.identity.common.exception.ErrorCode;
import com.vilka.app.identity.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        com.vilka.app.identity.user.entity.User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ApiException(ErrorCode.USER_NOT_FOUND));

        return User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .authorities(List.of()) // roles later
                .build();
    }
}
