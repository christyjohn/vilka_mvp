package com.vilka.app.identity.user.controller;

import com.vilka.app.identity.user.dto.CreateUserRequest;
import com.vilka.app.identity.user.dto.UserExistsResponse;
import com.vilka.app.identity.user.dto.UserResponse;
import com.vilka.app.identity.user.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Register user
    @PostMapping
    public UserResponse create(@Valid @RequestBody CreateUserRequest request) {
        return userService.createUser(request);
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
