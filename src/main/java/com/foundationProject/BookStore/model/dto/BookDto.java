package com.foundationProject.BookStore.model.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {
    @NotNull(message = "Book title is required")
    @NotBlank(message = "Book title is required")
    @NotEmpty(message = "Book title is required")
    private String bookTitle;

    private String bookDescription;
    @Min(value = 1, message = "Unit price must be greater than 0")
    private Long unitPrice;
    private Long boughtAmount;
    private Set<String> categoryNames;

    @NotNull(message = "Author name is required")
    @NotBlank(message = "Author name is required")
    @NotEmpty(message = "Author name is required")
    private String authorName;
}
