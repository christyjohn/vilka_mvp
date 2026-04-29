package com.vilka.app.catalog.common.util;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class IdentityUrlDebugger {

    @Value("${services.vendor.url}")
    private String identityUrl;

    @PostConstruct
    public void init() {
        System.out.println("VENDOR URL = " + identityUrl);
    }
}
