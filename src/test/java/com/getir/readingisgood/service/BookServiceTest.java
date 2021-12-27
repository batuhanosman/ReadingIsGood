package com.getir.readingisgood.service;

import com.getir.readingisgood.exception.ReadingIsGoodBaseException;
import com.getir.readingisgood.model.DoFactory;
import com.getir.readingisgood.model.DtoFactory;
import com.getir.readingisgood.model.request.CreateBookRequest;
import com.getir.readingisgood.model.request.UpdateBookRequest;
import com.getir.readingisgood.repository.BookRepository;
import com.getir.readingisgood.service.impl.BookServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.PageRequest;

import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BookServiceTest {

    private BookService bookService;
    private BookRepository bookRepository;

    @Before
    public void setUp(){
        bookRepository = mock(BookRepository.class);
        bookService = new BookServiceImpl(bookRepository);
    }

    @Test
    public void get_all_books_test(){
        when(bookRepository.findAll(PageRequest.of(0,20))).thenReturn(DoFactory.getPageImplBook());
        bookService.getAllBooks(0, 20);
    }

    @Test(expected = ReadingIsGoodBaseException.class)
    public void create_book_test(){
        when(bookRepository.save(Mockito.any())).thenReturn(DoFactory.getBook());
        when(bookRepository.findByName(Mockito.anyString())).thenReturn(null);
        bookService.createBook(DtoFactory.getCreateBookRequest());

        CreateBookRequest request = DtoFactory.getCreateBookRequest();
        request.getBookCreateDTO().setQty(0L);
        when(bookRepository.findByName(Mockito.anyString())).thenReturn(null);
        bookService.createBook(request);
    }

    @Test(expected = ReadingIsGoodBaseException.class)
    public void create_book_test_with_book_already_exist_error(){
        when(bookRepository.findByName(Mockito.anyString())).thenReturn(DoFactory.getBook());
        bookService.createBook(DtoFactory.getCreateBookRequest());
    }

    @Test(expected = ReadingIsGoodBaseException.class)
    public void update_book_test(){
        when(bookRepository.save(Mockito.any())).thenReturn(DoFactory.getBook());
        when(bookRepository.findById(Mockito.anyString())).thenReturn(Optional.ofNullable(DoFactory.getBook()));
        bookService.updateBook(DtoFactory.getUpdateBookRequest());

        when(bookRepository.findById(Mockito.anyString())).thenReturn(Optional.ofNullable(null));
        bookService.updateBook(DtoFactory.getUpdateBookRequest());

    }

    @Test(expected = ReadingIsGoodBaseException.class)
    public void update_book_test_with_quantity_error(){
        UpdateBookRequest request = DtoFactory.getUpdateBookRequest();
        request.setQuantity(-1L);
        when(bookRepository.save(Mockito.any())).thenReturn(DoFactory.getBook());
        when(bookRepository.findById(Mockito.anyString())).thenReturn(Optional.ofNullable(DoFactory.getBook()));
        bookService.updateBook(request);
    }
}
