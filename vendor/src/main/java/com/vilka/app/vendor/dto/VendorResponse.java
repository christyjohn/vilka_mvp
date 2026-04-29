package com.vilka.app.vendor.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class VendorResponse {
    private Long id;
    private Long userId;
    private String businessName;
    private String description;
    private boolean active;
    private LocalDateTime createdAt;
}
