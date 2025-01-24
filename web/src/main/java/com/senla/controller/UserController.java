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

    private final UserService userService;

    private final ModelMapper modelMapper;

    @GetMapping
    public String showListForm(Model model) {
        List<UserGetDto> users = userService.getAllUsers();
        model.addAttribute("users", users);

        return "user/list";
    }

    @GetMapping("/new")
    public String showNewForm(Model model) {
        if (!model.containsAttribute("user")) {
            model.addAttribute("user", new UserCreateDto());
        }

        return "user/create";
    }

    @PostMapping("/insert")
    public String createUser(@Valid @ModelAttribute("user") UserCreateDto user,
                                     BindingResult bindingResult,
                                     RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.user", bindingResult);
            redirectAttributes.addFlashAttribute("user", user);
            return "redirect:/user/new";
        }

        userService.createUser(user);
        return "redirect:/user";
    }

    @GetMapping("/edit")
    public String showEditForm(@RequestParam UUID id, Model model) {
        if (!model.containsAttribute("user")) {
            UserCreateDto user = modelMapper.map(userService.getUserById(id), UserCreateDto.class);
            model.addAttribute("user", user);
        }
        model.addAttribute("id", id);

        return "user/edit";
    }

    @PostMapping("/update")
    public String updateUser(@RequestParam UUID id,
                             @Valid @ModelAttribute("user") UserCreateDto user,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.user", bindingResult);
            redirectAttributes.addFlashAttribute("user", user);
            return "redirect:/user/edit?id=" + id;
        }

        userService.updateUser(user, id);
        return "redirect:/user";
    }

    @GetMapping("/delete")
    public String deleteUser(@RequestParam UUID id) {
        userService.deleteUser(id);
        return "redirect:/user";
    }
}
