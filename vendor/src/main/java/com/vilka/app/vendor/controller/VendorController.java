package com.vilka.app.vendor.controller;

import com.vilka.app.vendor.dto.VendorApplyRequest;
import com.vilka.app.vendor.dto.VendorResponse;
import com.vilka.app.vendor.service.VendorService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/vendors")
public class VendorController {

    private final VendorService vendorService;
    private static final Logger log = LoggerFactory.getLogger(VendorService.class);

    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    @GetMapping("/test")
    public String test(Authentication auth) {
        log.info("🔥 Vendor TEST HIT");
        return "User: " + auth.getName();
    }

    // User applies
    @PreAuthorize("hasAuthority('VENDOR_APPLY')")
    @PostMapping("/apply")
    public void apply(Authentication auth,
                      @RequestBody VendorApplyRequest request) {
        log.info("🔥 Vendor APPLY HIT");
        Long userId = Long.valueOf((String) auth.getPrincipal());
        System.out.println("🔥 userId -> " + userId);
        vendorService.apply(userId, request.getBusinessName(), request.getDescription());
        /*
        public void apply(@AuthenticationPrincipal Long userId,
                          @RequestBody VendorApplyRequest request) {

            log.info("🔥 Vendor APPLY HIT");

            System.out.println("🔥 userId -> " + userId);

            vendorService.apply(
                    userId,
                    request.getBusinessName(),
                    request.getDescription()
            );
        }
     */
    }
    /*
    public void apply(@AuthenticationPrincipal Long userId,
                      @RequestBody VendorApplyRequest request) {

        log.info("🔥 Vendor APPLY HIT");

        System.out.println("🔥 userId -> " + userId);

        vendorService.apply(
                userId,
                request.getBusinessName(),
                request.getDescription()
        );
    }
     */

    // Admin approves
    @PostMapping("/{userId}/approve")
    public void approve(@PathVariable Long userId) {
        vendorService.approve(userId);
    }

    // Admin rejects
    @PostMapping("/{userId}/reject")
    public void reject(@PathVariable Long userId) {
        vendorService.reject(userId);
    }

    // TODO - later (for creating a bigger profile page)
    @PostMapping
    public VendorResponse create(@Valid @RequestBody VendorApplyRequest request) {
        return vendorService.createVendorProfile(request);
    }

    // TODO - refactor the 2 methods below, both are doing same
    @GetMapping("/{id}")
    public VendorResponse get(@PathVariable Long id) {
        return vendorService.get(id);
    }

    @GetMapping("/by-user/{userId}")
    public VendorResponse getByUser(@PathVariable Long userId) {
        return vendorService.getByUserId(userId);
    }

    @PreAuthorize("hasRole('VENDOR')")
    @PostMapping("/api/vendor/service")
    public String createService() {
        return "Created!";
    }
}
