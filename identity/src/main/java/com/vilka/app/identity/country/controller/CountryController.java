package com.vilka.app.identity.country.controller;

import com.vilka.app.identity.country.dto.CountryResponse;
import com.vilka.app.identity.country.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/meta")
@RequiredArgsConstructor
public class CountryController {

    private final CountryService countryService;

    @GetMapping("/countries")
    public List<CountryResponse> getCountries() {
        return countryService.getAllCountries();
    }
}
