package com.vilka.app.catalog.offering.service;

import com.vilka.app.catalog.common.exception.ApiException;
import com.vilka.app.catalog.common.exception.ErrorCode;
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

    private static final Logger log =
            LoggerFactory.getLogger(OfferingService.class);

    private final OfferingRepository repository;

    public OfferingService(OfferingRepository repository) {
        this.repository = repository;
    }

    public OfferingResponse createOffering(
            Long vendorId,
            CreateOfferingRequest request
    ) {
        log.info("🔥 Creating offering with vendor id={}", vendorId);

        OfferingEntity entity = OfferingMapper.toEntity(request);

        // IMPORTANT
        entity.setVendorId(vendorId);

        OfferingEntity saved = repository.save(entity);

        log.info("🔥 Offering created successfully with id={}", saved.getId());

        return OfferingMapper.toResponse(saved);
    }

    public OfferingResponse getById(Long id) {

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
