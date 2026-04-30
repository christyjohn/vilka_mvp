package com.vilka.app.identity.profile.dto;

import com.vilka.app.identity.address.dto.AddressResponse;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class ProfileResponse {
    private Long userId;
    private String firstName;
    private String lastName;
    private String email;

    private String phone;
    private String gender;
    private LocalDate dateOfBirth;
    private String bio;

    private List<AddressResponse> addresses;
}
