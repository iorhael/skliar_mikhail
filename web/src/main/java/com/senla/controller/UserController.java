package com.senla.controller;

import com.senla.dto.user.UserCreateDto;
import com.senla.dto.user.UserGetDto;
import com.senla.service.UserService;
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
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private static final String LIST_PATH = "user/list";
    private static final String CREATE_PATH = "user/create";
    private static final String EDIT_PATH = "user/edit";
    private static final String LIST_REDIRECT_PATH = "redirect:/user";
    private static final String CREATE_REDIRECT_PATH = "redirect:/user/new";
    private static final String EDIT_REDIRECT_PATH = "redirect:/user/edit?id=";
    private static final String USER_ATTRIBUTE_NAME = "user";
    private static final String USERS_ATTRIBUTE_NAME = "users";

    private final UserService userService;

    private final ModelMapper modelMapper;

    @GetMapping
    public String showListForm(Model model) {
        List<UserGetDto> users = userService.getAllUsers();
        model.addAttribute(USERS_ATTRIBUTE_NAME, users);

        return LIST_PATH;
    }

    @GetMapping("/new")
    public String showNewForm(Model model) {
        if (!model.containsAttribute(USER_ATTRIBUTE_NAME)) {
            model.addAttribute(USER_ATTRIBUTE_NAME, new UserCreateDto());
        }

        return CREATE_PATH;
    }

    @PostMapping("/insert")
    public String createUser(@Valid @ModelAttribute(USER_ATTRIBUTE_NAME) UserCreateDto user,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.user", bindingResult);
            redirectAttributes.addFlashAttribute(USER_ATTRIBUTE_NAME, user);
            return CREATE_REDIRECT_PATH;
        }

        userService.createUser(user);
        return LIST_REDIRECT_PATH;
    }

    @GetMapping("/edit")
    public String showEditForm(@RequestParam UUID id, Model model) {
        if (!model.containsAttribute(USER_ATTRIBUTE_NAME)) {
            UserCreateDto user = modelMapper.map(userService.getUserById(id), UserCreateDto.class);
            model.addAttribute(USER_ATTRIBUTE_NAME, user);
        }
        model.addAttribute("id", id);

        return EDIT_PATH;
    }

    @PostMapping("/update")
    public String updateUser(@RequestParam UUID id,
                             @Valid @ModelAttribute(USER_ATTRIBUTE_NAME) UserCreateDto user,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.user", bindingResult);
            redirectAttributes.addFlashAttribute(USER_ATTRIBUTE_NAME, user);
            return EDIT_REDIRECT_PATH + id;
        }

        userService.updateUser(user, id);
        return LIST_REDIRECT_PATH;
    }

    @GetMapping("/delete")
    public String deleteUser(@RequestParam UUID id) {
        userService.deleteUser(id);
        return LIST_REDIRECT_PATH;
    }
}
