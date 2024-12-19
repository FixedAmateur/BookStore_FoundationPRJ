package com.foundationProject.BookStore.service;

import com.foundationProject.BookStore.model.dto.CategoryDto;
import com.foundationProject.BookStore.model.response.CategoryResponse;

import java.util.List;

public interface CategoryService {
    CategoryResponse addCategory(CategoryDto categoryRequest);

    CategoryResponse updateCategory(Long categoryId, CategoryDto categoryRequest);

    CategoryResponse getCategoryById(Long categoryId);

    List<CategoryResponse> getAllCategory();

    String deleteCategory(Long categoryId);

    CategoryResponse getCategoryByCategoryName(String categoryName);
}
