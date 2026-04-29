package com.vilka.app.identity.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UserResponse {
    private Long id;
    private String email;
    private String name;
    private boolean enabled;
    private LocalDateTime createdAt;
}
