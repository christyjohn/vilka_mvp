package com.vilka.app.vendor.dto;

import com.vilka.app.vendor.entity.VendorStatus;
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
    private VendorStatus status;
    private LocalDateTime createdAt;
}
