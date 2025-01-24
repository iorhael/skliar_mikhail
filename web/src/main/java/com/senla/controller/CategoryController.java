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

    private final CategoryService categoryService;

    private final ModelMapper modelMapper;

    @GetMapping
    public String showListForm(Model model) {
        List<CategoryGetDto> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);

        return "category/list";
    }

    @GetMapping("/new")
    public String showNewForm(Model model) {
        if (!model.containsAttribute("category")) {
            model.addAttribute("category", new CategoryCreateDto());
        }
        model.addAttribute("categories", categoryService.getAllCategories());

        return "category/create";
    }

    @PostMapping("/insert")
    public String createCategory(@Valid @ModelAttribute("category") CategoryCreateDto category,
                                 BindingResult bindingResult,
                                 RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.category", bindingResult);
            redirectAttributes.addFlashAttribute("category", category);
            return "redirect:/category/new";
        }

        if (category.getParentCategory().getId() == null) category.setParentCategory(null);

        categoryService.createCategory(category);
        return "redirect:/category";
    }

    @GetMapping("/edit")
    public String showEditForm(@RequestParam UUID id, Model model) {
        if (!model.containsAttribute("category")) {
            CategoryCreateDto category = modelMapper.map(categoryService.getCategoryById(id), CategoryCreateDto.class);
            model.addAttribute("category", category);
        }
        model.addAttribute("id", id);
        model.addAttribute("categories", categoryService.getAllCategories());

        return "category/edit";
    }

    @PostMapping("/update")
    public String updateCategory(@RequestParam UUID id,
                                 @Valid @ModelAttribute("category") CategoryCreateDto category,
                                 BindingResult bindingResult,
                                 RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.category", bindingResult);
            redirectAttributes.addFlashAttribute("category", category);
            return "redirect:/category/edit?id=" + id;
        }

        if (category.getParentCategory().getId() == null) category.setParentCategory(null);

        categoryService.updateCategory(category, id);
        return "redirect:/category";
    }

    @GetMapping("/delete")
    public String deleteCategory(@RequestParam UUID id) {
        categoryService.deleteCategory(id);
        return "redirect:/category";
    }
}
