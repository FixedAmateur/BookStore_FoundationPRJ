package com.foundationProject.BookStore.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthorDto {
    @NotNull(message = "Author name is required")
    @NotEmpty(message = "Author name is required")
    @NotBlank(message = "Author name is required")
    private String authorName;
}
