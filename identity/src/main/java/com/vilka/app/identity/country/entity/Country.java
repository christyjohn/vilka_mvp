package com.vilka.app.identity.country.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="countries")
@Getter
@Setter
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @NotBlank
    @Column(name="country_code")
    private String countryCode;

    @NotBlank
    @Column(name="country_name")
    private String countryName;

}
