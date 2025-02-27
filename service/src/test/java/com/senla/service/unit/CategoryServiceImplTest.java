package com.senla.service.unit;

import com.senla.dto.category.CategoryCreateDto;
import com.senla.dto.category.CategoryGetDto;
import com.senla.model.Category;
import com.senla.repository.CategoryRepository;
import com.senla.service.imp.CategoryServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoryServiceImplTest {
    static final String STRING_PLACEHOLDER =  "Test";

    @Mock
    CategoryRepository categoryRepository;

    @Mock
    ModelMapper modelMapper;

    @InjectMocks
    CategoryServiceImpl categoryService;

    Category category;

    @BeforeEach
    void setup() {
        category = new Category();
        category.setId(UUID.randomUUID());
        category.setName(STRING_PLACEHOLDER);
        category.setDescription(STRING_PLACEHOLDER);
    }

    @Test
    void givenParentId_whenCreateCategory_thenReturnCategoryGetDto() {
        CategoryCreateDto createDto = new CategoryCreateDto();
        createDto.setParentId(UUID.randomUUID());

        given(modelMapper.map(createDto, Category.class))
                .willReturn(category);
        given(categoryRepository.getReferenceById(createDto.getParentId()))
                .willReturn(new Category());
        given(categoryRepository.save(category))
                .willReturn(category);
        given(modelMapper.map(category, CategoryGetDto.class))
                .willReturn(new CategoryGetDto());

        assertThat(categoryService.createCategory(createDto))
                .isInstanceOf(CategoryGetDto.class);
        assertNotNull(category.getParentCategory());
    }

    @Test
    void withoutParentId_whenCreateCategory_thenReturnCategoryGetDto() {
        CategoryCreateDto createDto = new CategoryCreateDto();

        given(modelMapper.map(createDto, Category.class))
                .willReturn(category);
        given(categoryRepository.save(category))
                .willReturn(category);
        given(modelMapper.map(category, CategoryGetDto.class))
                .willReturn(new CategoryGetDto());

        assertThat(categoryService.createCategory(createDto))
                .isInstanceOf(CategoryGetDto.class);
        assertNull(category.getParentCategory());
    }

    @Test
    void givenCategory_whenGetCategoryById_thenReturnCategoryGetDto() {
        UUID id = category.getId();

        given(categoryRepository.findById(category.getId()))
                .willReturn(Optional.of(category));
        given(modelMapper.map(category, CategoryGetDto.class))
                .willReturn(new CategoryGetDto());

        assertThat(categoryService.getCategoryBy(id))
                .isInstanceOf(CategoryGetDto.class);
    }

    @Test
    void givenNoCategory_whenGetCategoryById_thenThrowEntityNotFoundException() {
        UUID id = category.getId();

        given(categoryRepository.findById(category.getId()))
                .willReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class,
                () -> categoryService.getCategoryBy(id));
        verify(modelMapper, never()).map(any(Category.class), eq(CategoryGetDto.class));
    }

    @Test
    void givenCategory_whenGetCategoryByName_thenReturnCategoryGetDto() {
        String name = category.getName();

        given(categoryRepository.findByName(name))
                .willReturn(Optional.of(category));
        given(modelMapper.map(category, CategoryGetDto.class))
                .willReturn(new CategoryGetDto());

        assertThat(categoryService.getCategoryByName(name))
                .isInstanceOf(CategoryGetDto.class);
    }

    @Test
    void givenNoCategory_whenGetCategoryByName_thenThrowEntityNotFoundException() {
        String name = category.getName();

        given(categoryRepository.findByName(name))
                .willReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class,
                () -> categoryService.getCategoryByName(name));
        verify(modelMapper, never()).map(any(Category.class), eq(CategoryGetDto.class));
    }

    @Test
    void givenListOfCategories_whenGetAllCategories_thenReturnListOfCategoryGetDto() {
        Category category1 = new Category();
        List<Category> categories = List.of(category, category1);

        given(categoryRepository.findAll())
                .willReturn(categories);
        given(modelMapper.map(any(Category.class), eq(CategoryGetDto.class)))
                .willReturn(new CategoryGetDto());

        assertThat(categoryService.getAllCategories())
                .hasSize(categories.size());
    }

    @Test
    void givenCategoryAndParentId_whenUpdateCategory_thenReturnCategoryGetDto() {
        CategoryCreateDto createDto = new CategoryCreateDto();
        createDto.setParentId(UUID.randomUUID());
        UUID id = category.getId();

        given(categoryRepository.findById(id))
                .willReturn(Optional.of(category));
        doNothing().when(modelMapper).map(createDto, category);
        given(categoryRepository.getReferenceById(createDto.getParentId()))
                .willReturn(new Category());
        given(modelMapper.map(category, CategoryGetDto.class))
                .willReturn(new CategoryGetDto());

        assertThat(categoryService.updateCategory(createDto, id))
                .isInstanceOf(CategoryGetDto.class);
        assertNotNull(category.getParentCategory());
    }

    @Test
    void givenCategoryWithoutParentId_whenUpdateCategory_thenReturnCategoryGetDto() {
        CategoryCreateDto createDto = new CategoryCreateDto();
        UUID id = category.getId();

        given(categoryRepository.findById(id))
                .willReturn(Optional.of(category));
        doNothing().when(modelMapper).map(createDto, category);
        given(modelMapper.map(category, CategoryGetDto.class))
                .willReturn(new CategoryGetDto());

        assertThat(categoryService.updateCategory(createDto, id))
                .isInstanceOf(CategoryGetDto.class);
        assertNull(category.getParentCategory());
    }

    @Test
    void givenNoCategory_whenUpdateCategory_thenThrowEntityNotFoundException() {
        CategoryCreateDto createDto = new CategoryCreateDto();
        UUID id = category.getId();

        given(categoryRepository.findById(id))
                .willReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class,
                () -> categoryService.updateCategory(createDto, id));
        verify(modelMapper, never()).map(any(Category.class), eq(CategoryGetDto.class));
    }

    @Test
    void whenDeleteCategory() {
        UUID id = category.getId();

        categoryService.deleteCategory(id);

        verify(categoryRepository, times(1)).deleteById(id);
    }
}
