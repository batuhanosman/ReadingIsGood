package com.getir.readingisgood.controller;

import com.getir.readingisgood.constants.ApiEndPoints;
import com.getir.readingisgood.model.dto.StatisticsDTO;
import com.getir.readingisgood.model.response.BaseApiResponse;
import com.getir.readingisgood.service.StatisticService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = ApiEndPoints.STATISTICS_API, produces = ApiEndPoints.RESPONSE_CONTENT_TYPE,
        consumes = MediaType.APPLICATION_JSON_VALUE)
@OpenAPIDefinition(tags = {@Tag(name = "reading-is-good-statistics-api", description = "Statistics Api")})
public class StatisticsController {

    private final StatisticService statisticService;

    public StatisticsController(StatisticService statisticService) {
        this.statisticService = statisticService;
    }

    @GetMapping(value = "/", consumes = MediaType.ALL_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get My Statistics", description = "Get My Statistics", security = {@SecurityRequirement(name = "bearerAuth")})
    public BaseApiResponse<List<StatisticsDTO>> getMyStatistics(){
        log.info("Get My Statistics");
        return new BaseApiResponse(statisticService.getMyStatistics());
    }

}
