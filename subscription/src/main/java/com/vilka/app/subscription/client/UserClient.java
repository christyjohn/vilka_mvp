package com.vilka.app.subscription.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "identity-service", url = "${services.identity.url}")
public interface UserClient {

    @GetMapping("/api/users/{id}/exists")
    ExistsResponse exists(@PathVariable("id") Long userId);

    class ExistsResponse {
        private boolean exists;

        public boolean isExists() { return exists; }
        public void setExists(boolean exists) { this.exists = exists; }
    }
}
