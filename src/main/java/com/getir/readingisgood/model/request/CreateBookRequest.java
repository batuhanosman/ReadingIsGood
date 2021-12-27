package com.getir.readingisgood.model.request;

import com.getir.readingisgood.model.dto.BookCreateDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateBookRequest {
    private BookCreateDTO bookCreateDTO;
}
