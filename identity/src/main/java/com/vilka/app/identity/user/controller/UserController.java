package com.vilka.app.identity.user.controller;

import com.vilka.app.identity.user.dto.UserExistsResponse;
import com.vilka.app.identity.user.dto.UserResponse;
import com.vilka.app.identity.user.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Get user
    @GetMapping("/{id}")
    public UserResponse get(@PathVariable Long id) {
        return userService.getUser(id);
    }

    // Critical for microservices
    @GetMapping("/{id}/exists")
    public UserExistsResponse exists(@PathVariable Long id) {
        return userService.checkUserExists(id);
    }
}
