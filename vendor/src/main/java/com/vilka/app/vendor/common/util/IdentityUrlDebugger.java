package com.vilka.app.vendor.common.util;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class IdentityUrlDebugger {

    @Value("${services.identity.url}")
    private String identityUrl;

    @PostConstruct
    public void init() {
        System.out.println("IDENTITY URL = " + identityUrl);
    }
}
