package com.getir.readingisgood.model.dto;

import com.getir.readingisgood.entity.Book;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class OrderDTO {

    private String id;

    private Long bookCount;

    private BigDecimal totalPrice;

    private LocalDate orderDate;

    private String userId;

    private List<Book> books;
}
