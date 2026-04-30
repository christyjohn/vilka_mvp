package com.vilka.app.identity.profile.entity;

import com.vilka.app.identity.common.base.entity.BaseEntity;
import com.vilka.app.identity.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "user_profiles")
@Getter
@Setter
public class Profile extends BaseEntity {

    @Id
    @Column(name = "user_id")
    private Long user_id;

    @MapsId // KEY: shares PK with User
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="phone")
    private String phone;

    @Column(name="gender")
    private String gender;

    @Column(name="date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name="bio")
    private String bio;

}
