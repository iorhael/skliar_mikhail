package com.senla.service.imp;

import com.senla.dto.category.CategoryCreateDto;
import com.senla.dto.category.CategoryGetDto;
import com.senla.model.Category;
import com.senla.repository.CategoryRepository;
import com.senla.service.CategoryService;
import com.senla.service.exception.ServiceException;
import com.senla.service.exception.category.CategoryDeleteException;
import com.senla.service.exception.category.CategoryUpdateException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    private final ModelMapper modelMapper;

    @Transactional
    @Override
    public CategoryGetDto createCategory(CategoryCreateDto category) {
        Category categoryEntity = modelMapper.map(category, Category.class);

        Category createdCategory = categoryRepository.create(categoryEntity);

        return modelMapper.map(createdCategory, CategoryGetDto.class);
    }

    @Transactional
    @Override
    public CategoryGetDto getCategoryById(UUID id) {
        return categoryRepository.findById(id)
                .map(category -> modelMapper.map(category, CategoryGetDto.class))
                .orElseThrow(() -> new ServiceException("No category found"));
    }

    @Transactional
    @Override
    public List<CategoryGetDto> getAllCategories() {
        return categoryRepository.findAll().stream()
                .map(category -> modelMapper.map(category, CategoryGetDto.class))
                .toList();
    }

    @Transactional
    @Override
    public CategoryGetDto updateCategory(CategoryCreateDto category, UUID id) {
        Category categoryEntity = modelMapper.map(category, Category.class);

        return categoryRepository.update(categoryEntity, id)
                .map(c -> modelMapper.map(c, CategoryGetDto.class))
                .orElseThrow(() -> new CategoryUpdateException("Can't update category"));
    }

    @Transactional
    @Override
    public CategoryGetDto deleteCategory(UUID id) {
        return categoryRepository.deleteById(id)
                .map(category -> modelMapper.map(category, CategoryGetDto.class))
                .orElseThrow(() -> new CategoryDeleteException("Can't delete category"));
    }
}
