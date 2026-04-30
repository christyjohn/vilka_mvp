package com.vilka.app.identity.address.service;

import com.vilka.app.identity.address.dto.AddressRequest;
import com.vilka.app.identity.address.dto.AddressResponse;
import com.vilka.app.identity.address.entity.Address;
import com.vilka.app.identity.address.mapper.AddressMapper;
import com.vilka.app.identity.address.repository.AddressRepository;
import com.vilka.app.identity.country.entity.Country;
import com.vilka.app.identity.country.repository.CountryRepository;
import com.vilka.app.identity.profile.entity.Profile;
import com.vilka.app.identity.profile.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressService {
    private final AddressRepository addressRepository;
    private final ProfileRepository profileRepository;
    private final CountryRepository countryRepository;
    private final AddressMapper addressMapper;

    private Long getCurrentUserId() {
        return 1L;
    }

    public List<AddressResponse> getMyAddresses() {
        return addressRepository.findByUserId(getCurrentUserId())
                .stream()
                .map(addressMapper::mapToResponse)
                .toList();
    }

    @Transactional
    public AddressResponse addAddress(AddressRequest request) {

        Profile profile = profileRepository.findById(getCurrentUserId())
                .orElseThrow();

        Country country = countryRepository.findById(request.getCountryCode())
                .orElseThrow(() -> new RuntimeException("Invalid country"));

        Address address = new Address();
        address.getUser().setProfile(profile);
        address.setCountry(country);

        addressMapper.mapRequest(address, request);

        return addressMapper.mapToResponse(addressRepository.save(address));
    }

    @Transactional
    public AddressResponse updateAddress(Long id, AddressRequest request) {

        Address address = addressRepository.findById(id)
                .orElseThrow();

        if (!address.getUser().getProfile().getUserId().equals(getCurrentUserId())) {
            throw new RuntimeException("Unauthorized");
        }

        Country country = countryRepository.findById(request.getCountryCode())
                .orElseThrow();

        address.setCountry(country);
        addressMapper.mapRequest(address, request);

        return addressMapper.mapToResponse(addressRepository.save(address));
    }
}
