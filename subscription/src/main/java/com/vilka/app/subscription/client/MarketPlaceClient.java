package com.vilka.app.subscription.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "catalog-service", url = "${services.catalog.url}")
public interface MarketPlaceClient {

    @GetMapping("/api/offerings/{id}")
    OfferingResponse getOffering(@PathVariable("id") Long serviceId);

    class OfferingResponse {
        private Long id;

        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }
    }
}
