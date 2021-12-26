package com.getir.readingisgood.entity;

import com.getir.readingisgood.model.dto.OrderDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EnableMongoAuditing
@Document(collection = "orders")
public class Order {

    @Id
    private String id;

    @Min(0)
    private Long bookCount;

    @DecimalMin(value = "0.0")
    private BigDecimal totalPrice;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate orderDate;

    private String userId;

    @DBRef(lazy = true)
    private List<Book> books;

    public OrderDTO toDTO(){
        return OrderDTO.builder()
                .id(getId())
                .bookCount(getBookCount())
                .totalPrice(getTotalPrice())
                .orderDate(getOrderDate())
                .userId(getUserId())
                .books(getBooks())
                .build();

    }

}
