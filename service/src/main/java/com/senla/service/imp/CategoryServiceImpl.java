package com.senla.service.imp;

import com.senla.di.annotation.Autowired;
import com.senla.di.annotation.Component;
import com.senla.dto.category.CategoryCreateDto;
import com.senla.dto.category.CategoryGetDto;
import com.senla.model.Category;
import com.senla.repository.CategoryRepository;
import com.senla.repository.exception.CategoryNotFoundException;
import com.senla.service.CategoryService;
import com.senla.service.exception.category.CategoryCreateException;
import com.senla.service.exception.category.CategoryDeleteException;
import com.senla.service.exception.category.CategoryUpdateException;
import com.senla.util.ModelMapperUtil;

import java.util.List;
import java.util.UUID;

@Component
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public CategoryGetDto createCategory(CategoryCreateDto category) {
        Category categoryEntity = ModelMapperUtil.MODEL_MAPPER.map(category, Category.class);

        return categoryRepository.create(categoryEntity)
                .map(c -> ModelMapperUtil.MODEL_MAPPER.map(c, CategoryGetDto.class))
                .orElseThrow(() -> new CategoryCreateException("Can't create category"));
    }

    @Override
    public CategoryGetDto getCategoryById(UUID id) {
        return categoryRepository.getById(id)
                .map(category -> ModelMapperUtil.MODEL_MAPPER.map(category, CategoryGetDto.class))
                .orElseThrow(() -> new CategoryNotFoundException("No category found"));
    }

    @Override
    public List<CategoryGetDto> getAllCategories() {
        return categoryRepository.getAll().stream()
                .map(category -> ModelMapperUtil.MODEL_MAPPER.map(category, CategoryGetDto.class))
                .toList();
    }

    @Override
    public CategoryGetDto updateCategory(CategoryCreateDto category, UUID id) {
        Category categoryEntity = ModelMapperUtil.MODEL_MAPPER.map(category, Category.class);

        return categoryRepository.update(categoryEntity, id)
                .map(c -> ModelMapperUtil.MODEL_MAPPER.map(c, CategoryGetDto.class))
                .orElseThrow(() -> new CategoryUpdateException("Can't update category"));
    }

    @Override
    public CategoryGetDto deleteCategory(UUID id) {
        return categoryRepository.delete(id)
                .map(category -> ModelMapperUtil.MODEL_MAPPER.map(category, CategoryGetDto.class))
                .orElseThrow(() -> new CategoryDeleteException("Can't delete category"));
    }
}
