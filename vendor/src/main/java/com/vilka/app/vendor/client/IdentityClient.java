package com.vilka.app.vendor.client;

import com.vilka.app.vendor.config.FeignClientConfig;
import com.vilka.app.vendor.dto.RoleRequest;
import com.vilka.app.vendor.dto.IdentityUserExistsResponse;
import com.vilka.app.vendor.dto.IdentityUserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "identity-service",
        url = "${services.identity.url}",
        configuration = FeignClientConfig.class
)
public interface IdentityClient {

    @GetMapping("/api/v1/users/{id}")
    IdentityUserResponse getUser(@PathVariable("id") Long userId);

    @GetMapping("/api/v1/users/{id}/exists")
    IdentityUserExistsResponse exists(@PathVariable("id") Long userId);

    @PostMapping("/api/v1/internal/users/{id}/roles")
    void updateUserRole(@PathVariable Long id, @RequestBody RoleRequest request);
}