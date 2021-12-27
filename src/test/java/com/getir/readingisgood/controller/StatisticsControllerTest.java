package com.getir.readingisgood.controller;

import com.getir.readingisgood.service.StatisticService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class StatisticsControllerTest {
    private MockMvc mockMvc;
    private StatisticsController statisticsController;
    private StatisticService statisticService;

    @Before
    public void setup(){
        statisticService = mock(StatisticService.class);
        statisticsController = new StatisticsController(statisticService);
        mockMvc = MockMvcBuilders.standaloneSetup(statisticsController).build();
    }

    @Test
    public void get_all_statisticss_by_date_interval_test() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/statistics/"))
                .andExpect(status().isOk()).andReturn();
    }
}
