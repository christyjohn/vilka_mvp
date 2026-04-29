package com.vilka.app.catalog.offering.controller;

import com.vilka.app.catalog.offering.dto.CreateOfferingRequest;
import com.vilka.app.catalog.offering.dto.OfferingResponse;
import com.vilka.app.catalog.offering.service.OfferingService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/offerings")
public class OfferingController {

    private final OfferingService service;

    public OfferingController(OfferingService service) {
        this.service = service;
    }

    @PostMapping
    public OfferingResponse create(@RequestBody CreateOfferingRequest request) {
        return service.createOffering(request);
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
