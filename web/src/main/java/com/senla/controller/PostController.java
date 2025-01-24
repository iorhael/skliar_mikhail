package com.senla.controller;

import com.senla.dto.post.PostCreateDto;
import com.senla.dto.post.PostGetDto;
import com.senla.dto.post.PostUpdateDto;
import com.senla.service.PostService;
import com.senla.service.SubscriptionPlanService;
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
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {
    private static final String LIST_PATH = "post/list";
    private static final String CREATE_PATH = "post/create";
    private static final String EDIT_PATH = "post/edit";
    private static final String LIST_REDIRECT_PATH = "redirect:/post";
    private static final String CREATE_REDIRECT_PATH = "redirect:/post/new";
    private static final String EDIT_REDIRECT_PATH = "redirect:/post/edit?id=";
    private static final String POST_ATTRIBUTE_NAME = "post";
    private static final String POSTS_ATTRIBUTE_NAME = "posts";
    private static final String AUTHORS_ATTRIBUTE_NAME = "authors";
    private static final String SUBSCRIPTION_PLANS_ATTRIBUTE_NAME = "subscriptionPlans";

    private final PostService postService;

    private final UserService userService;

    private final SubscriptionPlanService subscriptionPlanService;

    private final ModelMapper modelMapper;

    @GetMapping
    public String showListForm(Model model) {
        List<PostGetDto> posts = postService.getAllPosts();
        model.addAttribute(POSTS_ATTRIBUTE_NAME, posts);

        return LIST_PATH;
    }

    @GetMapping("/new")
    public String showNewForm(Model model) {
        if (!model.containsAttribute(POSTS_ATTRIBUTE_NAME)) {
            model.addAttribute(POST_ATTRIBUTE_NAME, new PostCreateDto());
        }
        model.addAttribute(AUTHORS_ATTRIBUTE_NAME, userService.getAllUsers());
        model.addAttribute(SUBSCRIPTION_PLANS_ATTRIBUTE_NAME, subscriptionPlanService.getAllSubscriptionPlans());

        return CREATE_PATH;
    }

    @PostMapping("/insert")
    public String createPost(@Valid @ModelAttribute(POST_ATTRIBUTE_NAME) PostCreateDto post,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.post", bindingResult);
            redirectAttributes.addFlashAttribute(POST_ATTRIBUTE_NAME, post);
            return CREATE_REDIRECT_PATH;
        }

        postService.createPost(post);
        return LIST_REDIRECT_PATH;
    }

    @GetMapping("/edit")
    public String showEditForm(@RequestParam UUID id, Model model) {
        if (!model.containsAttribute(POST_ATTRIBUTE_NAME)) {
            PostUpdateDto post = modelMapper.map(postService.getPostById(id), PostUpdateDto.class);
            model.addAttribute(POST_ATTRIBUTE_NAME, post);
        }
        model.addAttribute("id", id);

        return EDIT_PATH;
    }

    @PostMapping("/update")
    public String updatePost(@RequestParam UUID id,
                             @Valid @ModelAttribute(POST_ATTRIBUTE_NAME) PostUpdateDto post,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.post", bindingResult);
            redirectAttributes.addFlashAttribute(POST_ATTRIBUTE_NAME, post);
            return EDIT_REDIRECT_PATH + id;
        }

        postService.updatePost(post, id);
        return LIST_REDIRECT_PATH;
    }

    @GetMapping("/delete")
    public String deletePost(@RequestParam UUID id) {
        postService.deletePost(id);
        return LIST_REDIRECT_PATH;
    }
}
