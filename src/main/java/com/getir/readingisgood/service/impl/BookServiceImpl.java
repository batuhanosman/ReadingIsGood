package com.getir.readingisgood.service.impl;

import com.getir.readingisgood.constants.ApiErrorConstants;
import com.getir.readingisgood.entity.Book;
import com.getir.readingisgood.exception.ReadingIsGoodBaseException;
import com.getir.readingisgood.model.dto.BookDTO;
import com.getir.readingisgood.model.request.CreateBookRequest;
import com.getir.readingisgood.model.request.UpdateBookRequest;
import com.getir.readingisgood.repository.BookRepository;
import com.getir.readingisgood.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public PageImpl<BookDTO> getAllBooks(int page, int pageSize) {
        Page<Book> books = bookRepository.findAll(PageRequest.of(page, pageSize));
            return new PageImpl<BookDTO>(books.get().map(Book::toDTO).collect(Collectors.toList()),
                    books.getPageable(),
                    books.getTotalElements());
    }

    @Override
    public BookDTO createBook(CreateBookRequest request) {
        if(Objects.nonNull(bookRepository.findByName(request.getBookCreateDTO().getName())))
            throw new ReadingIsGoodBaseException(ApiErrorConstants.BOOK_ALREADY_EXIST);
        if(request.getBookCreateDTO().getQty() <= 0)
            throw new ReadingIsGoodBaseException(ApiErrorConstants.QTY_CANNOT_BE_LESS_THAN_OR_EQUAL_ZERO);
        log.info("Book is Creating {}", request.getBookCreateDTO());
        return bookRepository.save(new Book().fromDTO(request.getBookCreateDTO())).toDTO();
    }

    @Override
    public BookDTO updateBook(UpdateBookRequest request) {
        if(request.getQuantity() < 0)
            throw new ReadingIsGoodBaseException(ApiErrorConstants.QTY_CANNOT_BE_LESS_THAN_ZERO);
        Book book = bookRepository.findById(request.getId())
                .orElseThrow(() -> new ReadingIsGoodBaseException(ApiErrorConstants.BOOK_IS_NOT_FOUND));
        book.setQty(request.getQuantity());
        log.info("Book Updated {}", book);
        return bookRepository.save(book).toDTO();
    }
}
