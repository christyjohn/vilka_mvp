package com.vilka.app.vendor.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IdentityUserExistsResponse {
    private boolean exists;

    public IdentityUserExistsResponse(boolean exists) {
        this.exists = exists;
    }
}
