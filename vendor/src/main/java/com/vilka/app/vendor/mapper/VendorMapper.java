package com.vilka.app.vendor.mapper;

import com.vilka.app.vendor.dto.VendorApplyRequest;
import com.vilka.app.vendor.dto.VendorResponse;
import com.vilka.app.vendor.entity.Vendor;
import org.springframework.stereotype.Component;

@Component
public class VendorMapper {
    public static Vendor toEntity(VendorApplyRequest req) {
        Vendor v = new Vendor();
        //v.setUserId(req.getUserId()); // removed - TODO - more fields to add later
        v.setBusinessName(req.getBusinessName());
        return v;
    }

    public static VendorResponse toResponse(Vendor v) {
        VendorResponse res = new VendorResponse();
        res.setId(v.getId());
        res.setUserId(v.getUserId());
        res.setBusinessName(v.getBusinessName());
        res.setStatus(v.getStatus());
        res.setCreatedAt(v.getCreatedAt());
        return res;
    }
}
