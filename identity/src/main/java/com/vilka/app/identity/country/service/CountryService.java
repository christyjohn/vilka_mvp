package com.vilka.app.identity.country.service;

import com.vilka.app.identity.country.CountryMapper;
import com.vilka.app.identity.country.dto.CountryResponse;
import com.vilka.app.identity.country.repository.CountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CountryService {

    private CountryRepository countryRepository;
    private CountryMapper countryMapper;

    public List<CountryResponse> getAllCountries() {
        return countryRepository.findAll()
                .stream()
                .map(CountryMapper::toResponse)
                .toList();
    }
}
