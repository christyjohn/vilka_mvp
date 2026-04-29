package com.vilka.app.catalog.offering.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "vendor-offering", url = "${services.vendor.url}")
public interface VendorClient {

    @GetMapping("/api/vendors/{id}")
    VendorResponse getVendor(@PathVariable("id") Long vendorId);

    class VendorResponse {
        private Long id;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }
    }
}
