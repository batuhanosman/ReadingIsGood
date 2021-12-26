package com.getir.readingisgood.service;

import com.getir.readingisgood.model.dto.OrderDTO;
import com.getir.readingisgood.model.request.CreateOrderRequest;
import org.springframework.data.domain.PageImpl;

import java.time.LocalDate;

public interface OrderService {
    PageImpl<OrderDTO> getAllOrdersByDateInterval(int page, int pageSize, LocalDate startDate, LocalDate endDate);

    OrderDTO createOrder(CreateOrderRequest request);

    OrderDTO getOrderById(String id);
}
