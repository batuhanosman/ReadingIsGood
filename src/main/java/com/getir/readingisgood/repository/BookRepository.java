package com.getir.readingisgood.repository;

import com.getir.readingisgood.entity.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface BookRepository extends MongoRepository<Book, Long> {
    Book findByName(String name);

    Optional<Book> findById(String id);

    Optional<Book> findByIdAndQtyGreaterThan(String bookId, Long qty);
}
