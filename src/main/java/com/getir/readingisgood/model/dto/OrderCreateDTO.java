package com.getir.readingisgood.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderCreateDTO {

    @NotNull
    @Min(value = 1, message = "Quantity can not be less than one")
    private Long qty;

    @NotEmpty(message = "Book id can not be null")
    private String bookId;
}
