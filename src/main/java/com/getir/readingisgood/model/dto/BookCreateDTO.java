package com.getir.readingisgood.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookCreateDTO {
    @NotEmpty(message = "Name can not be empty")
    private String name;

    @NotEmpty(message = "Name can not be empty")
    private String author;

    private String genre;

    private String description;

    @DecimalMin(value = "0.0", message = "Price can not be less than zero")
    private BigDecimal price;

    @Min(value = 0, message = "Quantity can not be less than zero")
    private Long qty;
}
