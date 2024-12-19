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
public class OrderItemDto {
    @NotEmpty(message = "Book id is required")
    @NotNull(message = "Book id is required")
    @NotBlank(message = "Book id is required")
    private Long bookId;

    private Long quantity;

    private Long userId;
}
