package com.getir.readingisgood.model;

import com.getir.readingisgood.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.math.BigDecimal;
import java.util.Arrays;

public class DoFactory {
    public static Page<Book> getPageImplBook() {
        return new PageImpl<Book>(Arrays.asList(Book.builder()
                .name("The Hobbit")
                .author("J. R. R. Tolkien")
                .genre("Fantasy")
                .description("")
                .price(BigDecimal.valueOf(50))
                .qty(25L)
                .build()));
    }

    public static Book getBook() {
        return Book.builder()
                .name("The Hobbit")
                .author("J. R. R. Tolkien")
                .genre("Fantasy")
                .description("")
                .price(BigDecimal.valueOf(50))
                .qty(25L)
                .build();
    }
}
