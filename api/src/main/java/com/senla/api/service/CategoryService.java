package com.senla.api.service;

import com.senla.api.model.Category;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CategoryService {
    Optional<Category> createCategory(Category category);

    Optional<Category> getCategoryById(UUID id);

    List<Category> getAllCategories();

    Optional<Category> updateCategory(Category category, UUID id);

    Optional<Category> deleteCategory(UUID id);
}
