package com.getir.readingisgood.model.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;

@Builder
@Data
public class BookDTO {

    private String id;

    @NotEmpty(message = "Book name can not be empty")
    private String name;

    @NotEmpty(message = "Author can not be empty")
    private String author;

    private String genre;

    private String description;

    private BigDecimal price;

    private Long qty;

}
