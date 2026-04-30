package com.vilka.app.identity.country.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CountryResponse {
    private String code;
    private String name;
}