package com.vilka.app.vendor.dto;

import com.vilka.app.vendor.entity.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IdentityUserResponse {
    private Long id;
    private String email;
    private String name;
    private Role role;
    private boolean enabled;
}
