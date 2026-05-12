package com.vilka.app.catalog.offering.mapper;

import com.vilka.app.catalog.offering.dto.CreateOfferingRequest;
import com.vilka.app.catalog.offering.dto.OfferingResponse;
import com.vilka.app.catalog.offering.entity.OfferingEntity;

public class OfferingMapper {

    public static OfferingEntity toEntity(CreateOfferingRequest req) {
        OfferingEntity offering = new OfferingEntity();
        offering.setName(req.getName());
        offering.setDescription(req.getDescription());
        offering.setType(req.getType());
        offering.setPrice(req.getPrice());
        return offering;
    }

    public static OfferingResponse toResponse(OfferingEntity offering) {
        OfferingResponse res = new OfferingResponse();
        res.setId(offering.getId());
        res.setVendorId(offering.getVendorId());
        res.setName(offering.getName());
        res.setDescription(offering.getDescription());
        res.setType(offering.getType());
        res.setPrice(offering.getPrice());
        res.setActive(offering.isActive());
        res.setCreatedAt(offering.getCreatedAt());
        return res;
    }
}
