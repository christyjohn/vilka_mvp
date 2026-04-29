package com.vilka.app.identity.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserExistsResponse {
    private boolean exists;

    public UserExistsResponse(boolean exists) {
        this.exists = exists;
    }
}
