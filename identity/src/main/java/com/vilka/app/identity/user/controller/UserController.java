package com.vilka.app.identity.user.controller;

import com.vilka.app.identity.user.dto.UserExistsResponse;
import com.vilka.app.identity.user.dto.UserResponse;
import com.vilka.app.identity.user.service.UserService;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
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

    /*@GetMapping("/{id}")
    public String test(Authentication auth) {
        System.out.println(auth);
        return "ok";
    }*/

    // Get user
    @GetMapping("/{id}")
    public UserResponse getUser(@PathVariable Long id, Authentication authentication) {
        System.out.println("🔥 CONTROLLER AUTH: " + authentication);
        System.out.println("Auth class ==>" + authentication.getClass());
        System.out.println("Auth principal ==>" + authentication.getPrincipal());
        System.out.println("🔥 AUTH AUTHORITIES: " + authentication.getAuthorities());
        System.out.println("🔥 AUTH CREDENTIALS: " + authentication.getCredentials());
        System.out.println("🔥 AUTH DETAILS: " + authentication.getDetails());
        if (!authentication.isAuthenticated()) {
            throw new AccessDeniedException("Forbidden");
        }
        return userService.getUser(id);
    }

    // Critical for microservices
    @GetMapping("/{id}/exists")
    public UserExistsResponse exists(@PathVariable Long id) {
        return userService.checkUserExists(id);
    }

}
