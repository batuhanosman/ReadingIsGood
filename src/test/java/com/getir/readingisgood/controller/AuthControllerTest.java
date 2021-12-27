package com.getir.readingisgood.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.getir.readingisgood.authentication.controller.AuthController;
import com.getir.readingisgood.authentication.service.AuthService;
import com.getir.readingisgood.model.DtoFactory;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AuthControllerTest {
    private MockMvc mockMvc;
    private AuthController authController;
    private AuthService authService;

    @Before
    public void setup(){
        authService = mock(AuthService.class);
        authController = new AuthController(authService);
        mockMvc = MockMvcBuilders.standaloneSetup(authController).build();
    }

    @Test
    public void signin_test() throws Exception {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(DtoFactory.getSignInRequest());

        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/auth/signin")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(json))
                .andExpect(status().isOk()).andReturn();
    }

    @Test
    public void signup_test() throws Exception {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(DtoFactory.getSignUpRequest());

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/auth/signup")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(json))
                .andExpect(status().isOk()).andReturn();
    }
}
