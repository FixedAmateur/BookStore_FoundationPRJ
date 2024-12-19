package com.foundationProject.BookStore.service.impl;


import com.foundationProject.BookStore.exception.AppException;
import com.foundationProject.BookStore.exception.ErrorCode;
import com.foundationProject.BookStore.exception.ResourceNotFoundException;
import com.foundationProject.BookStore.model.dto.CategoryDto;
import com.foundationProject.BookStore.model.entity.Category;
import com.foundationProject.BookStore.model.response.CategoryResponse;
import com.foundationProject.BookStore.repository.CategoryRepository;
import com.foundationProject.BookStore.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    @Override
    public CategoryResponse addCategory(CategoryDto categoryRequest){
        if (categoryRepository.existsByCategoryName(categoryRequest.getCategoryName())){
            throw new AppException(ErrorCode.CATEGORY_EXISTED);
        }
        Category category = modelMapper.map(categoryRequest, Category.class);

        return modelMapper.map(categoryRepository.save(category), CategoryResponse.class);
    }

    @Override
    public CategoryResponse updateCategory(Long categoryId, CategoryDto categoryRequest){
        if (categoryRepository.existsByCategoryName(categoryRequest.getCategoryName())){
            throw new AppException(ErrorCode.CATEGORY_EXISTED);
        }
        Category category = categoryRepository.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","id",categoryId));
        modelMapper.map(categoryRequest,category);
        return modelMapper.map(categoryRepository.save(category), CategoryResponse.class);
    }

    @Override
    public CategoryResponse getCategoryById(Long categoryId){
            Category category = categoryRepository.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","id",categoryId));
            CategoryResponse response = modelMapper.map(category, CategoryResponse.class);
            return response;

    }

    @Override
    public List<CategoryResponse> getAllCategory() {
            List<CategoryResponse> categories = categoryRepository.findAll().stream().map(category -> modelMapper.map(category, CategoryResponse.class)).toList();
            return categories;

    }

    @Override
    public String deleteCategory(Long categoryId){
        Category category = categoryRepository.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","id",categoryId));
        categoryRepository.delete(category);
        return "Category with id: " +categoryId+ " was deleted successfully";
    }

    @Override
    public CategoryResponse getCategoryByCategoryName(String categoryName){
            Category categories = categoryRepository.findByCategoryName(categoryName).orElseThrow(()->new ResourceNotFoundException("Category","name",categoryName));
            CategoryResponse categoryResponse =  modelMapper.map(categories, CategoryResponse.class);
            return categoryResponse;

    }

}
