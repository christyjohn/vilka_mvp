package com.vilka.app.subscription.entity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OfferingResponse {

    private Long id;
    private String name;
    private BigDecimal price;
}
