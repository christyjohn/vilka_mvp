package com.vilka.app.catalog.offering.controller;

import com.vilka.app.catalog.offering.dto.CreateOfferingRequest;
import com.vilka.app.catalog.offering.dto.OfferingResponse;
import com.vilka.app.catalog.offering.service.OfferingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/offerings")
public class OfferingController {

    private final OfferingService service;
    private static final Logger log = LoggerFactory.getLogger(OfferingController.class);

    public OfferingController(OfferingService service) {
        this.service = service;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('CREATE_SERVICE')")
    public OfferingResponse create(Authentication auth,
                                   @RequestBody CreateOfferingRequest request) {
        Long vendorId = (Long) auth.getPrincipal();
        log.info("🔥 Offering CREATE HIT by Vendor Id: " + vendorId);
        return service.createOffering(vendorId, request);
    }

    @GetMapping("/{id}")
    public OfferingResponse get(@PathVariable Long id) {
        return service.get(id);
    }

    @GetMapping
    public List<OfferingResponse> getAll() {
        return service.getAll();
    }

        @GetMapping("/vendor/{vendorId}")
    public List<OfferingResponse> getByVendor(@PathVariable Long vendorId) {
        return service.getByVendor(vendorId);
    }
}
