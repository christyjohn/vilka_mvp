package com.vilka.app.identity.profile.controller;

import com.vilka.app.identity.profile.dto.ProfileRequest;
import com.vilka.app.identity.profile.dto.ProfileResponse;
import com.vilka.app.identity.profile.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/profile")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;

    @GetMapping("/me")
    public ProfileResponse getProfile() {
        return profileService.getMyProfile();
    }

    @PostMapping("/me")
    public ProfileResponse createProfile(@RequestBody ProfileRequest request) {
        return profileService.createProfile(request);
    }

    @PutMapping("/me")
    public ProfileResponse updateProfile(@RequestBody ProfileRequest request) {
        return profileService.updateProfile(request);
    }
}
