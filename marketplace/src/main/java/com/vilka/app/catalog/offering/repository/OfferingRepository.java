package com.vilka.app.catalog.offering.repository;

import com.vilka.app.catalog.offering.entity.OfferingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OfferingRepository extends JpaRepository<OfferingEntity, Long> {

    List<OfferingEntity> findByVendorId(Long vendorId);
}
