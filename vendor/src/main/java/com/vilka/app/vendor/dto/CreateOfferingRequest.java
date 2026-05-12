package com.vilka.app.vendor.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CreateOfferingRequest {

    @NotNull(message = "Vendor id is required")
    private Long vendorId;

    @NotBlank(message = "Offering name is needed")
    private String name;

    private String description;

    @NotBlank(message="Type should be given (LIVE_CLASS, VIDEO, SERVICE etc)")
    private String type;

    @NotBlank(message = "Price is needed.")
    private BigDecimal price;

}
