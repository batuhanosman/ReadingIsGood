package com.getir.readingisgood.model.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class OrderCreateDTO {

    @NotNull
    @Min(value = 1, message = "Quantity can not be less than one")
    private Long qty;

    @NotEmpty(message = "Book id can not be null")
    private String bookId;
}
