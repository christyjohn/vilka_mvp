package com.vilka.app.catalog.offering.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class OfferingResponse {
    private Long id;
    private Long vendorId;
    private String name;
    private String description;
    private String type;
    private BigDecimal price;
    private boolean active;
    private LocalDateTime createdAt;
}
