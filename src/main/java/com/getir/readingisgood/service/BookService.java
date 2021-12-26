package com.getir.readingisgood.service;

import com.getir.readingisgood.model.dto.BookDTO;
import com.getir.readingisgood.model.request.CreateBookRequest;
import com.getir.readingisgood.model.request.UpdateBookRequest;
import org.springframework.data.domain.PageImpl;

public interface BookService {
    PageImpl<BookDTO> getAllBooks(int page, int pageSize);

    BookDTO createBook(CreateBookRequest request);

    BookDTO updateBook(UpdateBookRequest request);
}
