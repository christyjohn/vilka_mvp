package com.vilka.app.identity.address.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddressResponse {
    private Long id;
    private String street_address;
    private String locality;
    private String city;
    private String state;
    private String postalCode;

    private String countryCode;
    private String countryName;
}
