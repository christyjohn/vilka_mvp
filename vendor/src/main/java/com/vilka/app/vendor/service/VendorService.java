package com.vilka.app.vendor.service;

import com.vilka.app.vendor.client.UserClient;
import com.vilka.app.vendor.common.exception.ApiException;
import com.vilka.app.vendor.common.exception.ErrorCode;
import com.vilka.app.vendor.dto.CreateVendorRequest;
import com.vilka.app.vendor.dto.VendorResponse;
import com.vilka.app.vendor.entity.Vendor;
import com.vilka.app.vendor.mapper.VendorMapper;
import com.vilka.app.vendor.repository.VendorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class VendorService {

    private final VendorRepository vendorRepository;
    private final UserClient userClient;
    private static final Logger log = LoggerFactory.getLogger(VendorService.class);

    public VendorService(VendorRepository vendorRepository, UserClient userClient) {
        this.vendorRepository = vendorRepository;
        this.userClient = userClient;
    }

    public VendorResponse create(CreateVendorRequest request) {

        log.info("Creating user with userId={}", request.getUserId());

        // 🔥 Validate user via Identity Service
        boolean userExists = userClient.exists(request.getUserId()).isExists();

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

        return VendorMapper.toResponse(saved);
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
