package com.vilka.app.identity.profile.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ProfileRequest {
    private String phone;
    private String gender;
    private LocalDate dateOfBirth;
    private String bio;
}
