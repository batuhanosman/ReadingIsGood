package com.getir.readingisgood.controller;

import com.getir.readingisgood.service.CustomerService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CustomerControllerTest {
    private MockMvc mockMvc;
    private CustomerController customerController;
    private CustomerService customerService;

    @Before
    public void setup(){
        customerService = mock(CustomerService.class);
        customerController = new CustomerController(customerService);
        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
    }

    @Test
    public void get_all_books_test() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/customer/")
                        .contentType(MediaType.ALL_VALUE)
                        .param("page","0")
                        .param("pageSize", "20"))
                .andExpect(status().isOk()).andReturn();
    }
}
