package com.vilka.app.vendor.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateVendorRequest {

    @NotNull(message = "user id is required")
    private Long userId;

    @NotBlank(message = "Business Name is required")
    private String businessName;
}
