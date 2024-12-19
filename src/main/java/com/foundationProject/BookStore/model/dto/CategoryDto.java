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
public class CategoryDto {
    @NotNull(message = "Category name is required")
    @NotBlank(message = "Category name is required")
    @NotEmpty(message = "Category name is required")
    private String categoryName;
}
