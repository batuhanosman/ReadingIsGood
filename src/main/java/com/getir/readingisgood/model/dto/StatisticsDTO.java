package com.getir.readingisgood.model.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class StatisticsDTO {

    private String month;

    private BigDecimal totalOrderAmount;

    private Long totalBookCount;

    private int totalOrderCount;

    private String userId;
}
