package com.senla.service.imp;

import com.senla.dto.category.CategoryCreateDto;
import com.senla.dto.category.CategoryGetDto;
import com.senla.model.Category;
import com.senla.repository.CategoryRepository;
import com.senla.service.CategoryService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    public static final String CATEGORY_NOT_FOUND = "Category not found";

    private final CategoryRepository categoryRepository;

    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public CategoryGetDto createCategory(CategoryCreateDto categoryCreateDto) {
        Category category = modelMapper.map(categoryCreateDto, Category.class);

        Optional.ofNullable(categoryCreateDto.getParentId())
                .map(categoryRepository::getReferenceById)
                .ifPresent(category::setParentCategory);

        categoryRepository.save(category);

        return modelMapper.map(category, CategoryGetDto.class);
    }

    @Override
    public CategoryGetDto getCategoryById(UUID id) {
        return categoryRepository.findById(id)
                .map(category -> modelMapper.map(category, CategoryGetDto.class))
                .orElseThrow(() -> new EntityNotFoundException(CATEGORY_NOT_FOUND));
    }

    @Override
    public List<CategoryGetDto> getAllCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(category -> modelMapper.map(category, CategoryGetDto.class))
                .toList();
    }

    @Override
    @Transactional
    public CategoryGetDto updateCategory(CategoryCreateDto categoryCreateDto, UUID id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(CATEGORY_NOT_FOUND));

        Optional.ofNullable(categoryCreateDto.getParentId())
                .map(categoryRepository::getReferenceById)
                .ifPresent(category::setParentCategory);

        category.setName(categoryCreateDto.getName());
        category.setDescription(categoryCreateDto.getDescription());

        return modelMapper.map(category, CategoryGetDto.class);
    }

    @Override
    public void deleteCategory(UUID id) {
        categoryRepository.deleteById(id);
    }
}
