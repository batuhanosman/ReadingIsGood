package com.getir.readingisgood.model.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class UpdateBookRequest {
    @NotEmpty
    private String id;
    @NotNull
    private Long quantity;
}
