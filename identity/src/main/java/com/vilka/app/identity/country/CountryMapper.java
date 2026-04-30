package com.vilka.app.identity.country;

import com.vilka.app.identity.country.dto.CountryResponse;
import com.vilka.app.identity.country.entity.Country;
import org.springframework.stereotype.Component;

@Component
public class CountryMapper {

    public static CountryResponse toResponse(Country country) {
        return CountryResponse.builder()
                .code(country.getCountryCode())
                .name(country.getCountryName())
                .build();
    }
}
