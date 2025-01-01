package com.senla.service;

import com.senla.dto.category.CategoryCreateDto;
import com.senla.dto.category.CategoryGetDto;

import java.util.List;
import java.util.UUID;

public interface CategoryService {
    CategoryGetDto createCategory(CategoryCreateDto category);

    CategoryGetDto getCategoryById(UUID id);

    List<CategoryGetDto> getAllCategories();

    CategoryGetDto updateCategory(CategoryCreateDto category, UUID id);

    CategoryGetDto deleteCategory(UUID id);
}
