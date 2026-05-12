package com.vilka.app.vendor.client;

import com.vilka.app.vendor.dto.CreateOfferingRequest;
import com.vilka.app.vendor.dto.OfferingResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "marketplace",
        url = "${services.marketplace.url}"
)
public interface MarketplaceClient {

    @PostMapping("/api/v1/offerings")
    OfferingResponse createOffering(
            @RequestBody CreateOfferingRequest request
    );
}
