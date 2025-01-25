package com.senla.controller;

import com.senla.dto.category.CategoryCreateDto;
import com.senla.dto.category.CategoryGetDto;
import com.senla.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {
    private static final String LIST_PATH = "category/list";
    private static final String CREATE_PATH = "category/create";
    private static final String EDIT_PATH = "category/edit";
    private static final String LIST_REDIRECT_PATH = "redirect:/category";
    private static final String CREATE_REDIRECT_PATH = "redirect:/category/new";
    private static final String EDIT_REDIRECT_PATH = "redirect:/category/edit?id=";
    private static final String CATEGORY_ATTRIBUTE_NAME = "category";
    private static final String CATEGORIES_ATTRIBUTE_NAME = "categories";

    private final CategoryService categoryService;

    private final ModelMapper modelMapper;

    @GetMapping
    public String showListForm(Model model) {
        List<CategoryGetDto> categories = categoryService.getAllCategories();
        model.addAttribute(CATEGORIES_ATTRIBUTE_NAME, categories);

        return LIST_PATH;
    }

    @GetMapping("/new")
    public String showNewForm(Model model) {
        if (!model.containsAttribute(CATEGORY_ATTRIBUTE_NAME)) {
            model.addAttribute(CATEGORY_ATTRIBUTE_NAME, new CategoryCreateDto());
        }
        model.addAttribute(CATEGORIES_ATTRIBUTE_NAME, categoryService.getAllCategories());

        return CREATE_PATH;
    }

    @PostMapping("/insert")
    public String createCategory(@Valid @ModelAttribute(CATEGORY_ATTRIBUTE_NAME) CategoryCreateDto category,
                                 BindingResult bindingResult,
                                 RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.category", bindingResult);
            redirectAttributes.addFlashAttribute(CATEGORY_ATTRIBUTE_NAME, category);
            return CREATE_REDIRECT_PATH;
        }

        if (category.getParentCategory().getId() == null) category.setParentCategory(null);

        categoryService.createCategory(category);
        return LIST_REDIRECT_PATH;
    }

    @GetMapping("/edit")
    public String showEditForm(@RequestParam UUID id, Model model) {
        if (!model.containsAttribute(CATEGORY_ATTRIBUTE_NAME)) {
            CategoryCreateDto category = modelMapper.map(categoryService.getCategoryById(id), CategoryCreateDto.class);
            model.addAttribute(CATEGORY_ATTRIBUTE_NAME, category);
        }
        model.addAttribute("id", id);
        model.addAttribute(CATEGORIES_ATTRIBUTE_NAME, categoryService.getAllCategories());

        return EDIT_PATH;
    }

    @PostMapping("/update")
    public String updateCategory(@RequestParam UUID id,
                                 @Valid @ModelAttribute(CATEGORY_ATTRIBUTE_NAME) CategoryCreateDto category,
                                 BindingResult bindingResult,
                                 RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.category", bindingResult);
            redirectAttributes.addFlashAttribute(CATEGORY_ATTRIBUTE_NAME, category);
            return EDIT_REDIRECT_PATH + id;
        }

        if (category.getParentCategory().getId() == null) category.setParentCategory(null);

        categoryService.updateCategory(category, id);
        return LIST_REDIRECT_PATH;
    }

    @GetMapping("/delete")
    public String deleteCategory(@RequestParam UUID id) {
        categoryService.deleteCategory(id);
        return LIST_REDIRECT_PATH;
    }
}
