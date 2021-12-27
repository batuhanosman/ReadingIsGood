package com.getir.readingisgood.entity;

import com.getir.readingisgood.model.dto.BookCreateDTO;
import com.getir.readingisgood.model.dto.BookDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EnableMongoAuditing
@Document(collection = "books")
public class Book {

    @Id
    private String id;

    @Version
    private int version;

    @NotEmpty
    private String name;

    @NotEmpty
    private String author;

    private String genre;

    private String description;

    @DecimalMin(value = "0.0")
    private BigDecimal price;

    @Min(0)
    private Long qty;

    public BookDTO toDTO(){
       return BookDTO.builder()
                .id(getId())
                .name(getName())
                .author(getAuthor())
                .genre(getGenre())
                .description(getDescription())
                .price(getPrice())
                .qty(getQty())
                .build();

    }

    public Book fromDTO(BookCreateDTO dto){
        return Book.builder()
                    .name(dto.getName())
                    .author(dto.getAuthor())
                    .genre(dto.getGenre())
                    .description(dto.getDescription())
                    .price(dto.getPrice())
                    .qty(dto.getQty())
                    .build();
    }
}
