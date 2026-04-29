package com.vilka.app.vendor.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateVendorRequest {

    private Long userId;
    private String businessName;
}
