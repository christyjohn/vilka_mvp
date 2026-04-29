package com.vilka.app.catalog.offering.service;

import com.vilka.app.catalog.common.exception.ApiException;
import com.vilka.app.catalog.common.exception.ErrorCode;
import com.vilka.app.catalog.offering.client.VendorClient;
import com.vilka.app.catalog.offering.dto.CreateOfferingRequest;
import com.vilka.app.catalog.offering.dto.OfferingResponse;
import com.vilka.app.catalog.offering.entity.OfferingEntity;
import com.vilka.app.catalog.offering.mapper.OfferingMapper;
import com.vilka.app.catalog.offering.repository.OfferingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OfferingService {

    private final OfferingRepository repository;
    private final VendorClient vendorClient;

    private static final Logger log = LoggerFactory.getLogger(OfferingService.class);

    public OfferingService(OfferingRepository repository, VendorClient vendorClient) {
        this.repository = repository;
        this.vendorClient = vendorClient;
    }

    public OfferingResponse createOffering(CreateOfferingRequest request) {

        log.info("Creating offering with venfor if={}", request.getVendorId());

        // Validate vendor via Vendor Service
        try {
            vendorClient.getVendor(request.getVendorId());
        } catch (Exception ex) {
            log.warn("Offering creation failed: vendor not found: {}", request.getVendorId());
            throw new ApiException(ErrorCode.VENDOR_NOT_FOUND);
        }

        OfferingEntity entity = OfferingMapper.toEntity(request);
        OfferingEntity saved = repository.save(entity);

        log.info("Offering created successfully with id={}", saved.getId());

        return OfferingMapper.toResponse(saved);
    }

    public OfferingResponse get(Long id) {
        OfferingEntity entity = repository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Offering not found with id={}", id);
                    return new ApiException(ErrorCode.OFFERING_NOT_FOUND);
                });

        return OfferingMapper.toResponse(entity);
    }

    public List<OfferingResponse> getByVendor(Long vendorId) {
        return repository.findByVendorId(vendorId)
                .stream()
                .map(OfferingMapper::toResponse)
                .collect(Collectors.toList());
    }

    public List<OfferingResponse> getAll() {
        return repository.findAll()
                .stream()
                .map(OfferingMapper::toResponse)
                .collect(Collectors.toList());
    }
}
