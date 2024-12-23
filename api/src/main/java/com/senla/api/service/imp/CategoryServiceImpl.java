package com.senla.api.service.imp;

import com.senla.api.dao.CategoryDao;
import com.senla.api.dao.exception.CategoryNotFoundException;
import com.senla.api.dto.category.CategoryCreateDto;
import com.senla.api.dto.category.CategoryGetDto;
import com.senla.api.model.Category;
import com.senla.api.service.CategoryService;
import com.senla.api.service.exception.category.CategoryCreateException;
import com.senla.api.service.exception.category.CategoryDeleteException;
import com.senla.api.service.exception.category.CategoryUpdateException;
import com.senla.api.util.ModelMapperUtil;
import com.senla.di.annotation.Autowired;
import com.senla.di.annotation.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryDao categoryDao;

    @Override
    public CategoryGetDto createCategory(CategoryCreateDto category) {
        Category categoryEntity = ModelMapperUtil.getConfiguredMapper().map(category, Category.class);

        return categoryDao.create(categoryEntity)
                .map(c -> ModelMapperUtil.getConfiguredMapper().map(c, CategoryGetDto.class))
                .orElseThrow(() -> new CategoryCreateException("Can't create category"));
    }

    @Override
    public CategoryGetDto getCategoryById(UUID id) {
        return categoryDao.getById(id)
                .map(category -> ModelMapperUtil.getConfiguredMapper().map(category, CategoryGetDto.class))
                .orElseThrow(() -> new CategoryNotFoundException("No category found"));
    }

    @Override
    public List<CategoryGetDto> getAllCategories() {
        List<Category> categories = categoryDao.getAll();
        List<CategoryGetDto> categoryGetDtos = new ArrayList<>();

        for (Category category : categories) {
            CategoryGetDto categoryGetDto = ModelMapperUtil.getConfiguredMapper().map(category, CategoryGetDto.class);
            categoryGetDtos.add(categoryGetDto);
        }

        return categoryGetDtos;
    }

    @Override
    public CategoryGetDto updateCategory(CategoryCreateDto category, UUID id) {
        Category categoryEntity = ModelMapperUtil.getConfiguredMapper().map(category, Category.class);

        return categoryDao.update(categoryEntity, id)
                .map(c -> ModelMapperUtil.getConfiguredMapper().map(c, CategoryGetDto.class))
                .orElseThrow(() -> new CategoryUpdateException("Can't update category"));
    }

    @Override
    public CategoryGetDto deleteCategory(UUID id) {
        return categoryDao.delete(id)
                .map(category -> ModelMapperUtil.getConfiguredMapper().map(category, CategoryGetDto.class))
                .orElseThrow(() -> new CategoryDeleteException("Can't delete category"));
    }
}
