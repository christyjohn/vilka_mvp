package com.vilka.app.subscription.client;

import com.vilka.app.subscription.common.config.FeignClientConfig;
import com.vilka.app.subscription.entity.OfferingResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "marketplace-service",
        url = "${services.marketplace.url}",
        configuration = FeignClientConfig.class)
public interface MarketplaceClient {

    @GetMapping("/api/v1/offerings/{id}")
    OfferingResponse getOffering(@PathVariable("id") Long offeringId);

}
