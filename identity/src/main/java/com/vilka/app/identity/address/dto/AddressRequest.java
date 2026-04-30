package com.vilka.app.identity.address.dto;

import lombok.Data;

@Data
public class AddressRequest {
    private String street_address;
    private String locality;
    private String city;
    private String state;
    private String postalCode;
    private String countryCode;
}
