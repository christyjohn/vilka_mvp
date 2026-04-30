package com.vilka.app.identity.address.mapper;

import com.vilka.app.identity.address.dto.AddressRequest;
import com.vilka.app.identity.address.dto.AddressResponse;
import com.vilka.app.identity.address.entity.Address;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper {

    public void mapRequest(Address a, AddressRequest r) {
        a.setStreetAddress(r.getStreet_address());
        a.setLocality(r.getLocality());
        a.setCity(r.getCity());
        a.setState(r.getState());
        a.setPostalCode(r.getPostalCode());
    }

    public AddressResponse mapToResponse(Address a) {
        return AddressResponse.builder()
                .id(a.getId())
                .street_address(a.getStreetAddress())
                .locality(a.getLocality())
                .city(a.getCity())
                .state(a.getState())
                .postalCode(a.getPostalCode())
                .countryCode(a.getCountry().getCountryCode())
                .countryName(a.getCountry().getCountryName())
                .build();
    }
}
