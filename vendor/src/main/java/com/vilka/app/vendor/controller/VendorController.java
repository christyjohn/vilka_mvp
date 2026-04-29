package com.vilka.app.vendor.controller;

import com.vilka.app.vendor.dto.CreateVendorRequest;
import com.vilka.app.vendor.dto.VendorResponse;
import com.vilka.app.vendor.service.VendorService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vendors")
public class VendorController {

    private final VendorService vendorService;

    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    @PostMapping
    public VendorResponse create(@Valid @RequestBody CreateVendorRequest request) {
        return vendorService.create(request);
    }

    @GetMapping("/{id}")
    public VendorResponse get(@PathVariable Long id) {
        return vendorService.get(id);
    }

    @GetMapping("/by-user/{userId}")
    public VendorResponse getByUser(@PathVariable Long userId) {
        return vendorService.getByUserId(userId);
    }
}
