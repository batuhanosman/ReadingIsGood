package com.getir.readingisgood.mongodb;

import com.getir.readingisgood.entity.Book;
import com.getir.readingisgood.entity.Role;
import com.getir.readingisgood.entity.User;
import com.getir.readingisgood.model.enums.ERole;
import com.getir.readingisgood.repository.BookRepository;
import com.getir.readingisgood.repository.RoleRepository;
import com.getir.readingisgood.repository.UserRepository;
import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;
import java.util.Set;

@ChangeLog(order = "001")
public class DatabaseChangeLog {

    @ChangeSet(order = "001", author = "batu", id = "roles")
    public void createRoles(RoleRepository roleRepository){
        roleRepository.save(Role.builder().name(ERole.ROLE_ADMIN).build());
        roleRepository.save(Role.builder().name(ERole.ROLE_USER).build());
    }

    @ChangeSet(order = "002", author = "batu", id = "users")
    public void createUsers(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder){
        userRepository.save(User.builder()
                .username("admin")
                .password(passwordEncoder.encode("123456"))
                .email("osman.demirtas95@gmail.com")
                .roles(Set.of(roleRepository.findByName(ERole.ROLE_ADMIN).get()))
                .build());

        userRepository.save(User.builder()
                .username("user")
                .password(passwordEncoder.encode("123456"))
                .email("osman.demirtas1995@gmail.com")
                .roles(Set.of(roleRepository.findByName(ERole.ROLE_USER).get()))
                .build());
    }

    @ChangeSet(order = "003", author = "batu", id = "books")
    public void createBooks(BookRepository bookRepository){
        bookRepository.save(Book.builder()
                .name("The Hobbit")
                .author("J. R. R. Tolkien")
                .genre("Fantasy")
                .description("")
                .price(BigDecimal.valueOf(50))
                .qty(25L)
                .build());

        bookRepository.save(Book.builder()
                .name("Harry Potter and the Philosopher's Stone")
                .author("J. K. Rowling")
                .genre("Fantasy")
                .description("")
                .price(BigDecimal.valueOf(45))
                .qty(35L)
                .build());

        bookRepository.save(Book.builder()
                .name("The Little Prince")
                .author("Antoine de Saint-Exupéry")
                .genre("Novella")
                .description("")
                .price(BigDecimal.valueOf(25))
                .qty(30L)
                .build());

        bookRepository.save(Book.builder()
                .name("The Da Vinci Code")
                .author("Dan Brown")
                .genre("Mystery thriller")
                .description("")
                .price(BigDecimal.valueOf(35))
                .qty(5L)
                .build());
    }

}
