package com.getir.readingisgood.model.request;

import com.getir.readingisgood.model.dto.OrderCreateDTO;
import lombok.Data;

import java.util.List;

@Data
public class CreateOrderRequest {
    private List<OrderCreateDTO> orderCreateDTO;
}
