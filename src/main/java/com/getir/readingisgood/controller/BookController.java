package com.getir.readingisgood.controller;

import com.getir.readingisgood.constants.ApiEndPoints;
import com.getir.readingisgood.model.dto.BookDTO;
import com.getir.readingisgood.model.request.CreateBookRequest;
import com.getir.readingisgood.model.request.UpdateBookRequest;
import com.getir.readingisgood.model.response.BaseApiResponse;
import com.getir.readingisgood.service.BookService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping(value = ApiEndPoints.BOOK_API, produces = ApiEndPoints.RESPONSE_CONTENT_TYPE,
        consumes = MediaType.APPLICATION_JSON_VALUE)
@OpenAPIDefinition(tags = {@Tag(name = "reading-is-good-book-api", description = "Book Api")})
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Create New Boook", description = "Create New Boook", security = {@SecurityRequirement(name = "bearerAuth")})
    public BaseApiResponse<BookDTO> createBook(@Valid @RequestBody CreateBookRequest request){
        log.info("Create Book Request {}", request);
        return new BaseApiResponse(bookService.createBook(request));
    }

    @GetMapping(value = "/", consumes = MediaType.ALL_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMIN')OR hasRole('USER')")
    @Operation(summary = "Get All Books", description = "Get All Books", security = {@SecurityRequirement(name = "bearerAuth")})
    public BaseApiResponse<PageImpl<BookDTO>> getAllBooks(@RequestParam(defaultValue = "0") int page,
                                                          @RequestParam(defaultValue = "20") int pageSize ){
        log.info("Get All Books");
        return new BaseApiResponse(bookService.getAllBooks(page, pageSize));
    }

    @PutMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Update Book", description = "Update Book", security = {@SecurityRequirement(name = "bearerAuth")})
    public BaseApiResponse<BookDTO> updateBook(@Valid @RequestBody UpdateBookRequest request){
        log.info("Update Book Request {}", request);
        return new BaseApiResponse(bookService.updateBook(request));
    }

}
