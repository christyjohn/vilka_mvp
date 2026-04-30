package com.vilka.app.identity.profile.mapper;

import com.vilka.app.identity.profile.dto.ProfileRequest;
import com.vilka.app.identity.profile.dto.ProfileResponse;
import com.vilka.app.identity.profile.entity.Profile;
import org.springframework.stereotype.Component;

@Component
public class ProfileMapper {

    public void mapRequestToProfile(ProfileRequest req, Profile p) {
        p.setPhone(req.getPhone());
        p.setGender(req.getGender());
        p.setDateOfBirth(req.getDateOfBirth());
        p.setBio(req.getBio());
    }

    public ProfileResponse mapToResponse(Profile p) {
        return ProfileResponse.builder()
                .userId(p.getUserId())
                .firstName(p.getFirstName())
                .lastName(p.getLastName())
                .email(p.getUser().getEmail())
                .phone(p.getPhone())
                .gender(p.getGender())
                .dateOfBirth(p.getDateOfBirth())
                .bio(p.getBio())
                .build();
    }
}
