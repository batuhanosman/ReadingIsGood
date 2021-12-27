package com.getir.readingisgood.model.request;

import com.getir.readingisgood.model.dto.OrderCreateDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderRequest {
    private List<OrderCreateDTO> orderCreateDTO;
}
