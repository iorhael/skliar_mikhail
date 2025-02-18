package com.senla.controller;

import com.senla.controller.dto.ResponseInfoDto;
import com.senla.dto.category.CategoryCreateDto;
import com.senla.dto.category.CategoryGetDto;
import com.senla.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {
    private static final String CATEGORY_DELETION_MESSAGE = "Category with id %s deleted successfully";

    private final CategoryService categoryService;

    @GetMapping
    public List<CategoryGetDto> findAllCategories() {
        return categoryService.getAllCategories();
    }

    @GetMapping("/{id}")
    public CategoryGetDto findCategoryById(@PathVariable UUID id) {
        return categoryService.getCategoryBy(id);
    }

    @PostMapping
    @PreAuthorize("hasRole('AUTHOR')")
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryGetDto createCategory(@Valid @RequestBody CategoryCreateDto category) {
        return categoryService.createCategory(category);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('AUTHOR')")
    public CategoryGetDto updateCategory(@Valid @RequestBody CategoryCreateDto category,
                                         @PathVariable UUID id) {
        return categoryService.updateCategory(category, id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('AUTHOR')")
    public ResponseInfoDto deleteCategory(@PathVariable UUID id) {
        categoryService.deleteCategory(id);

        return ResponseInfoDto.builder()
                .message(String.format(CATEGORY_DELETION_MESSAGE, id))
                .build();
    }
}
