package com.vilka.app.identity.user.controller;

import com.vilka.app.identity.user.dto.RoleRequest;
import com.vilka.app.identity.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/internal/users")
@RequiredArgsConstructor
public class InternalUserController {

    private final UserService userService;

    @PostMapping("/{id}/roles")
    public void updateRole(@PathVariable Long id,
                           @RequestBody RoleRequest request) {
        userService.updateRole(id, request.getRole());
    }
}
