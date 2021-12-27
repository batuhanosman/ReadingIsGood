package com.getir.readingisgood.controller;

import com.getir.readingisgood.constants.ApiEndPoints;
import com.getir.readingisgood.model.dto.OrderDTO;
import com.getir.readingisgood.model.response.BaseApiResponse;
import com.getir.readingisgood.service.CustomerService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value = ApiEndPoints.CUSTOMER_API, produces = ApiEndPoints.RESPONSE_CONTENT_TYPE,
        consumes = MediaType.APPLICATION_JSON_VALUE)
@OpenAPIDefinition(tags = {@Tag(name = "reading-is-good-customer-api", description = "Customer Api")})
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }


    @GetMapping(value = "/", consumes = MediaType.ALL_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "Get My Orders", description = "Get My Orders", security = {@SecurityRequirement(name = "bearerAuth")})
    public BaseApiResponse<PageImpl<OrderDTO>> getMyOrders(@RequestParam(defaultValue = "0") int page,
                                                           @RequestParam(defaultValue = "20") int pageSize ){
        log.info("Get My Orders");
        return new BaseApiResponse(customerService.myOrders(page, pageSize));
    }
}
