package com.getir.readingisgood.service;

import com.getir.readingisgood.model.dto.OrderDTO;
import org.springframework.data.domain.PageImpl;

public interface CustomerService {
    PageImpl<OrderDTO> myOrders(int page, int pageSize);
}
