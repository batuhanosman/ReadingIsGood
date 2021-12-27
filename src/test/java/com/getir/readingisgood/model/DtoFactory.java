package com.getir.readingisgood.model;

import com.getir.readingisgood.authentication.model.request.SignUpRequest;
import com.getir.readingisgood.authentication.model.request.SigninRequest;
import com.getir.readingisgood.model.dto.BookCreateDTO;
import com.getir.readingisgood.model.dto.OrderCreateDTO;
import com.getir.readingisgood.model.enums.ERole;
import com.getir.readingisgood.model.request.CreateBookRequest;
import com.getir.readingisgood.model.request.CreateOrderRequest;
import com.getir.readingisgood.model.request.UpdateBookRequest;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Set;

public class DtoFactory {
    public static SigninRequest getSignInRequest(){
        return SigninRequest.builder()
                .username("testUser")
                .password("123456")
                .build();
    }
    public static SignUpRequest getSignUpRequest(){
        return SignUpRequest.builder()
                .username("testUser")
                .email("testEmail@example.com")
                .password("123456")
                .roles(Set.of(ERole.ROLE_ADMIN.name()))
                .build();
    }
    public static BookCreateDTO getBookCreateDTO(){
        return BookCreateDTO.builder()
                .name("The Hobbit")
                .author("J. R. R. Tolkien")
                .genre("Fantasy")
                .description("")
                .price(BigDecimal.valueOf(50))
                .qty(25L)
                .build();
    }
    public static CreateBookRequest getCreateBookRequest(){
        return CreateBookRequest.builder()
                .bookCreateDTO(getBookCreateDTO())
                .build();
    }

    public static UpdateBookRequest getUpdateBookRequest(){
        return UpdateBookRequest.builder()
                .id("1")
                .quantity(15L)
                .build();
    }

    public static CreateOrderRequest getCreateOrderRequest(){
        return CreateOrderRequest.builder()
                .orderCreateDTO(Arrays.asList(getOrderCreateDTO()))
                .build();
    }

    public static OrderCreateDTO getOrderCreateDTO(){
        return OrderCreateDTO.builder()
                .bookId("1")
                .qty(10L)
                .build();
    }
}
