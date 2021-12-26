package com.getir.readingisgood.repository;

import com.getir.readingisgood.entity.Role;
import com.getir.readingisgood.model.enums.ERole;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RoleRepository extends MongoRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
