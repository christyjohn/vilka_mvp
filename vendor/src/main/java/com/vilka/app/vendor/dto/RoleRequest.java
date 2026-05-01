package com.vilka.app.vendor.dto;

import com.vilka.app.vendor.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleRequest {

    private Role role; // "VENDOR"
}
