package com.vilka.app.vendor.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VendorApplyRequest {
    // TODO - Add more fields

    @NotBlank(message = "Business Name is required")
    private String businessName;

    private String description;
}
