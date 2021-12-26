package com.getir.readingisgood.service.impl;

import com.getir.readingisgood.authentication.service.impl.UserDetailsImpl;
import com.getir.readingisgood.constants.ApiErrorConstants;
import com.getir.readingisgood.entity.Book;
import com.getir.readingisgood.entity.Order;
import com.getir.readingisgood.entity.User;
import com.getir.readingisgood.exception.ReadingIsGoodBaseException;
import com.getir.readingisgood.model.dto.OrderDTO;
import com.getir.readingisgood.model.request.CreateOrderRequest;
import com.getir.readingisgood.repository.BookRepository;
import com.getir.readingisgood.repository.OrderRepository;
import com.getir.readingisgood.repository.UserRepository;
import com.getir.readingisgood.service.OrderService;
import io.jsonwebtoken.lang.Collections;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    public OrderServiceImpl(OrderRepository orderRepository, BookRepository bookRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    @Override
    public PageImpl<OrderDTO> getAllOrdersByDateInterval(int page, int pageSize, LocalDate startDate, LocalDate endDate) {
        Page<Order> orders = orderRepository.findAllByOrderDateIsBetween(startDate, endDate, PageRequest.of(page, pageSize));

        if(orders.isEmpty()) throw new ReadingIsGoodBaseException(ApiErrorConstants.ORDER_NOT_FOUND);

        return new PageImpl<OrderDTO>(orders.get().map(Order::toDTO).collect(Collectors.toList()),
                orders.getPageable(),
                orders.getTotalElements());
    }

    @Override
    public OrderDTO createOrder(CreateOrderRequest request) {

        AtomicReference<Long> bookCount = new AtomicReference<>(0L);
        AtomicReference<BigDecimal> totalPrice = new AtomicReference<>(BigDecimal.ZERO);
        List<Book> books = new ArrayList<>();

        request.getOrderCreateDTO().forEach(order -> {

            if(order.getQty().compareTo(0L) <= 0)
                throw new ReadingIsGoodBaseException(ApiErrorConstants.QTY_CANNOT_BE_LESS_THAN_OR_EQUAL_ZERO);

          Optional<Book> book = bookRepository.findByIdAndQtyGreaterThan(order.getBookId(), 0L);
          if(book.isPresent()){
            if(book.get().getQty().compareTo(order.getQty()) < 0)
                throw new ReadingIsGoodBaseException(ApiErrorConstants.THERE_ARE_NOT_ENOUGH_BOOKS_BY_NAME + book.get().getName());

            bookCount.updateAndGet(count -> count + order.getQty());
            totalPrice.set(totalPrice.get().add(book.get().getPrice().multiply(BigDecimal.valueOf(order.getQty()))));
            book.get().setQty(book.get().getQty() - order.getQty());
            bookRepository.save(book.get());
            log.info("Book Updated {}", book.get());
            books.add(book.get());

          }else{
              throw new ReadingIsGoodBaseException(ApiErrorConstants.BOOK_IS_NOT_FOUND);
          }

        });

        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByUsername(userDetails.getUsername()).get();

        Order order = orderRepository.save(Order.builder()
                .bookCount(bookCount.get())
                .totalPrice(totalPrice.get())
                .orderDate(LocalDate.now())
                .userId(user.getId())
                .books(books)
                .build());

        if(Collections.isEmpty(user.getOrders()))
            user.setOrders(new ArrayList<>());
        user.getOrders().add(order);
        userRepository.save(user);
        log.info("User Orders Updated {}", user);
        return order.toDTO();
    }

    @Override
    public OrderDTO getOrderById(String id) {
        Optional<Order> order = orderRepository.findById(id);
        if(order.isPresent())
            return order.get().toDTO();

        throw new ReadingIsGoodBaseException(ApiErrorConstants.ORDER_NOT_FOUND);
    }
}
