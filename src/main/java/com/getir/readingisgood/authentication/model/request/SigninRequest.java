package com.getir.readingisgood.authentication.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SigninRequest {
    @NotBlank
    private String username;

    @NotBlank
    private String password;
}
