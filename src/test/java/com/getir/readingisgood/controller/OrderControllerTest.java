package com.getir.readingisgood.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.getir.readingisgood.model.DtoFactory;
import com.getir.readingisgood.service.OrderService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class OrderControllerTest {
    private MockMvc mockMvc;
    private OrderController orderController;
    private OrderService orderService;

    @Before
    public void setup(){
        orderService = mock(OrderService.class);
        orderController = new OrderController(orderService);
        mockMvc = MockMvcBuilders.standaloneSetup(orderController).build();
    }

    @Test
    public void get_all_orders_by_date_interval_test() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/order/")
                        .contentType(MediaType.ALL_VALUE)
                        .param("page","0")
                        .param("pageSize", "20")
                        .param("startDate", "2020-10-10")
                        .param("endDate", "2022-10-10"))
                .andExpect(status().isOk()).andReturn();
    }

    @Test
    public void get_order_by_id_test() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/order/1")
                        .contentType(MediaType.ALL_VALUE))
                .andExpect(status().isOk()).andReturn();
    }

    @Test
    public void create_order_test() throws Exception {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(DtoFactory.getCreateOrderRequest());

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/order/")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(json))
                .andExpect(status().isOk()).andReturn();
    }
}
