package com.getir.readingisgood.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseApiResponse<T> {
    private String message;
    private T data;

    public BaseApiResponse(T data) {
        this.data = data;
    }
}
