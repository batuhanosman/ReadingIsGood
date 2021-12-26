package com.getir.readingisgood.repository;

import com.getir.readingisgood.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface OrderRepository extends MongoRepository<Order, Long> {
    Page<Order> findAllByOrderDateIsBetween(LocalDate startDate, LocalDate endDate, PageRequest pageRequest);
    Optional<Order> findById(String id);
    Page<Order> findAllByUserId(String userId, PageRequest of);
    List<Order> findAllByUserId(String userId);
}
