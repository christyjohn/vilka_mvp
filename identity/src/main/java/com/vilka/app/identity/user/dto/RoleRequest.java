package com.vilka.app.identity.user.dto;

import com.vilka.app.identity.user.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleRequest {

    private Role role; // "VENDOR"
}
