package com.foundationProject.BookStore.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterDto{
    private String fullName;
    @NotNull(message = "Username is required")
    @NotEmpty(message = "Username is required")
    @NotBlank(message = "Username is required")
    private String username;
    private String email;
    @NotNull(message = "Password is required")
    @NotBlank(message = "Password is required")
    @NotEmpty(message = "Password is required")
    private String password;

}
