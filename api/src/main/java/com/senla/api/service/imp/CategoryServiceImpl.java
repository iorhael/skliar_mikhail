package com.senla.api.service.imp;

import com.senla.api.dao.CategoryDao;
import com.senla.api.model.Category;
import com.senla.api.service.CategoryService;
import com.senla.di.annotation.Autowired;
import com.senla.di.annotation.Component;
import com.senla.di.annotation.Value;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryDao categoryDao;

    @Value("very_useful_service_value")
    private String veryUsefulServiceValue;

    @Override
    public Optional<Category> createCategory(Category category) {
        return categoryDao.create(category);
    }

    @Override
    public Optional<Category> getCategoryById(UUID id) {
        return categoryDao.getById(id);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryDao.getAll();
    }

    @Override
    public Optional<Category> updateCategory(Category category, UUID id) {
        return categoryDao.update(category, id);
    }

    @Override
    public Optional<Category> deleteCategory(UUID id) {
        return categoryDao.delete(id);
    }
}
