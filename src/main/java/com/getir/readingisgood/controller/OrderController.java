package com.getir.readingisgood.controller;

import com.getir.readingisgood.constants.ApiEndPoints;
import com.getir.readingisgood.model.dto.OrderDTO;
import com.getir.readingisgood.model.request.CreateOrderRequest;
import com.getir.readingisgood.model.response.BaseApiResponse;
import com.getir.readingisgood.service.OrderService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageImpl;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;

@Slf4j
@RestController
@RequestMapping(value = ApiEndPoints.ORDER_API, produces = ApiEndPoints.RESPONSE_CONTENT_TYPE,
        consumes = MediaType.APPLICATION_JSON_VALUE)
@OpenAPIDefinition(tags = {@Tag(name = "reading-is-good-order-api", description = "Order Api")})
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping(value = "/{ID}", consumes = MediaType.ALL_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get Order By Id", description = "Get Order By Id", security = {@SecurityRequirement(name = "bearerAuth")})
    public BaseApiResponse<OrderDTO> getOrderById(@PathVariable("ID") String id){
        log.info("Get All Orders By Date Interval");
        return new BaseApiResponse(orderService.getOrderById(id));
    }

    @GetMapping(value = "/", consumes = MediaType.ALL_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get All Orders By Date Interval", description = "Get All Orders By Date Interval", security = {@SecurityRequirement(name = "bearerAuth")})
    public BaseApiResponse<PageImpl<OrderDTO>> getAllOrdersByDateInterval(@RequestParam(defaultValue = "0") int page,
                                                                          @RequestParam(defaultValue = "20") int pageSize,
                                                                          @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
                                                                          @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate){
        log.info("Get All Orders By Date Interval");
        return new BaseApiResponse(orderService.getAllOrdersByDateInterval(page, pageSize, startDate, endDate));
    }

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Create New Order", description = "Create New Order", security = {@SecurityRequirement(name = "bearerAuth")})
    public BaseApiResponse<OrderDTO> createOrder(@Valid @RequestBody CreateOrderRequest request){
        log.info("Create Order Request {}", request);
        return new BaseApiResponse(orderService.createOrder(request));
    }
}
