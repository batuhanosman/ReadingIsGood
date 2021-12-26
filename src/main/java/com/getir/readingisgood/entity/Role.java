package com.getir.readingisgood.entity;

import com.getir.readingisgood.model.enums.ERole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "roles")
public class Role {
    @Id
    private Integer id;
    private ERole name;

    public Role(ERole name) {
        this.name = name;
    }
}
