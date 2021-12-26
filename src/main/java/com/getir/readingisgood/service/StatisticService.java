package com.getir.readingisgood.service;

import com.getir.readingisgood.model.dto.StatisticsDTO;

import java.util.List;

public interface StatisticService {
    List<StatisticsDTO> getMyStatistics();
}
