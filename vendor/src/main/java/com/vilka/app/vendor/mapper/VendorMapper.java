package com.vilka.app.vendor.mapper;

import com.vilka.app.vendor.dto.CreateVendorRequest;
import com.vilka.app.vendor.dto.VendorResponse;
import com.vilka.app.vendor.entity.Vendor;
import org.springframework.stereotype.Component;

@Component
public class VendorMapper {
    public static Vendor toEntity(CreateVendorRequest req) {
        Vendor v = new Vendor();
        v.setUserId(req.getUserId());
        v.setBusinessName(req.getBusinessName());
        return v;
    }

    public static VendorResponse toResponse(Vendor v) {
        VendorResponse res = new VendorResponse();
        res.setId(v.getId());
        res.setUserId(v.getUserId());
        res.setBusinessName(v.getBusinessName());
        res.setActive(v.isActive());
        res.setCreatedAt(v.getCreatedAt());
        return res;
    }
}
