package com.vilka.app.vendor.service;

import com.vilka.app.vendor.client.IdentityClient;
import com.vilka.app.vendor.common.exception.ApiException;
import com.vilka.app.vendor.common.exception.ErrorCode;
import com.vilka.app.vendor.config.security.JwtProperties;
import com.vilka.app.vendor.dto.RoleRequest;
import com.vilka.app.vendor.dto.VendorApplyRequest;
import com.vilka.app.vendor.dto.VendorResponse;
import com.vilka.app.vendor.entity.Role;
import com.vilka.app.vendor.entity.Vendor;
import com.vilka.app.vendor.entity.VendorStatus;
import com.vilka.app.vendor.mapper.VendorMapper;
import com.vilka.app.vendor.repository.VendorRepository;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class VendorService {

    private final VendorRepository vendorRepository;
    private final IdentityClient identityClient;
    private final JwtProperties jwtProperties;
    private static final Logger log = LoggerFactory.getLogger(VendorService.class);

    public VendorService(VendorRepository vendorRepository, IdentityClient userClient, JwtProperties jwtProperties) {
        this.vendorRepository = vendorRepository;
        this.identityClient = userClient;
        this.jwtProperties = jwtProperties;
    }

    // TODO - only used for debugging, once done, can delete the JwTProperties class
    //  and this code
    @PostConstruct
    public void debugSecret() {
        System.out.println("🔥 JWT SECRET = " + jwtProperties.getSecret());
    }

    public void upgradeToVendor(Long userId) {

        // Call Identity Service
        identityClient.updateUserRole(
                userId,
                new RoleRequest(Role.VENDOR)
        );
    }

    public void apply(Long userId, String businessName, String description) {

        log.info("🔥 Calling Identity...");
        identityClient.getUser(userId);

        // Prevent duplicate applications
        vendorRepository.findByUserId(userId).ifPresent(v -> {
            throw new RuntimeException("Already applied");
        });

        Vendor vendor = Vendor.builder()
                .userId(userId)
                .businessName(businessName)
                .description(description)
                .status(VendorStatus.PENDING)
                .build();

        vendorRepository.save(vendor);
    }

    public void approve(Long userId) {

        Vendor profile = vendorRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Vendor not found"));

        profile.setStatus(VendorStatus.APPROVED);
        vendorRepository.save(profile);

        // Call Identity Service to upgrade role
        identityClient.updateUserRole(userId, new RoleRequest(Role.VENDOR));
    }

    public void reject(Long userId) {

        Vendor vendor = vendorRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Vendor not found"));

        vendor.setStatus(VendorStatus.REJECTED);
        vendorRepository.save(vendor);
    }

    // TODO - later (for creating a bigger profile page)
    public VendorResponse createVendorProfile(VendorApplyRequest request) {

        // TODO - implement later
        /*log.info("Creating user with userId={}", request.getUserId());

        // Validate user via Identity Service
        boolean userExists = identityClient.exists(request.getUserId()).isExists();

        if (!userExists) {
            throw new ApiException(ErrorCode.VENDOR_NOT_FOUND);
        }

        // Prevent duplicate vendor per user
        if (vendorRepository.existsByUserId(request.getUserId())) {
            log.warn("Vendor creation failed: userId already exists: {}", request.getUserId());
            throw new ApiException(ErrorCode.VENDOR_ALREADY_EXIST);
        }

        Vendor vendor = VendorMapper.toEntity(request);
        Vendor saved = vendorRepository.save(vendor);

        log.info("Vendor  created successfully with id={}", saved.getId());

        return VendorMapper.toResponse(saved);*/
        return null;
    }

    public VendorResponse get(Long id) {
        log.debug("Fetching vendor with id={}", id);
        Vendor vendor = vendorRepository.findById(id)
                .orElseThrow(() -> new ApiException(ErrorCode.VENDOR_NOT_FOUND));

        return VendorMapper.toResponse(vendor);
    }

    public VendorResponse getByUserId(Long userId) {
        Vendor vendor = vendorRepository.findByUserId(userId)
                .orElseThrow(() -> new ApiException(ErrorCode.VENDOR_NOT_FOUND));

        return VendorMapper.toResponse(vendor);
    }
}
