package com.getir.readingisgood.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.getir.readingisgood.model.DtoFactory;
import com.getir.readingisgood.service.BookService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class BookControllerTest {
    private MockMvc mockMvc;
    private BookController bookController;
    private BookService bookService;

    @Before
    public void setup(){
        bookService = mock(BookService.class);
        bookController = new BookController(bookService);
        mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();
    }


    @Test
    public void create_book_test() throws Exception {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(DtoFactory.getCreateBookRequest());

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/book/")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(json))
                .andExpect(status().isOk()).andReturn();
    }

    @Test
    public void update_book_test() throws Exception {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(DtoFactory.getUpdateBookRequest());

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/api/book/")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(json))
                .andExpect(status().isOk()).andReturn();
    }

    @Test
    public void get_all_books_test() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/book/")
                        .contentType(MediaType.ALL_VALUE)
                        .param("page","0")
                        .param("pageSize", "20"))
                .andExpect(status().isOk()).andReturn();
    }
}
