package com.vilka.app.identity.profile.service;

import com.vilka.app.identity.profile.dto.ProfileRequest;
import com.vilka.app.identity.profile.dto.ProfileResponse;
import com.vilka.app.identity.profile.entity.Profile;
import com.vilka.app.identity.profile.mapper.ProfileMapper;
import com.vilka.app.identity.profile.repository.ProfileRepository;
import com.vilka.app.identity.user.entity.User;
import com.vilka.app.identity.user.repository.UserRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final ProfileRepository profileRepository;
    private final UserRepository userRepository;
    private final ProfileMapper profileMapper;

    private Long getCurrentUserId() {
        return 1L; // replace with SecurityContext
    }

    @Transactional(readOnly = true)
    public ProfileResponse getMyProfile() {
        Profile profile = profileRepository.findById(getCurrentUserId())
                .orElseThrow(() -> new RuntimeException("Profile not found"));

        return profileMapper.mapToResponse(profile);
    }

    @Transactional
    public ProfileResponse createProfile(ProfileRequest request) {

        User user = userRepository.findById(getCurrentUserId())
                .orElseThrow();

        Profile profile = new Profile();
        profile.setUser(user);
        profile.setFirstName(user.getEmail()); // placeholder

        profileMapper.mapRequestToProfile(request, profile);

        return profileMapper.mapToResponse(profileRepository.save(profile));
    }

    @Transactional
    public ProfileResponse updateProfile(ProfileRequest request) {

        Profile profile = profileRepository.findById(getCurrentUserId())
                .orElseThrow();

        profileMapper.mapRequestToProfile(request, profile);

        return profileMapper.mapToResponse(profileRepository.save(profile));
    }
}
