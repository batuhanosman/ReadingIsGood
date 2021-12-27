package com.getir.readingisgood.authentication.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequest {
    @NotBlank
    @Size(min = 3, max = 20)
    @NotNull(message = "username cannot be null")
    private String username;

    @NotBlank
    @Size(max = 50)
    @NotNull(message = "email cannot be null")
    @Email
    private String email;

    @NotBlank
    @Size(min = 6, max = 40)
    @NotNull(message = "password cannot be null")
    private String password;

    private Set<String> roles;
}
