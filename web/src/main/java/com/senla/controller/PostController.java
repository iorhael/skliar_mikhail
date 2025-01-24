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

    private final PostService postService;

    private final UserService userService;

    private final SubscriptionPlanService subscriptionPlanService;

    private final ModelMapper modelMapper;

    @GetMapping
    public String showListForm(Model model) {
        List<PostGetDto> posts = postService.getAllPosts();
        model.addAttribute("posts", posts);

        return "post/list";
    }

    @GetMapping("/new")
    public String showNewForm(Model model) {
        if (!model.containsAttribute("post")) {
            model.addAttribute("post", new PostCreateDto());
        }
        model.addAttribute("authors", userService.getAllUsers());
        model.addAttribute("subscriptionPlans", subscriptionPlanService.getAllSubscriptionPlans());

        return "post/create";
    }

    @PostMapping("/insert")
    public String createPost(@Valid @ModelAttribute("post") PostCreateDto post,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.post", bindingResult);
            redirectAttributes.addFlashAttribute("post", post);
            return "redirect:/post/new";
        }

        postService.createPost(post);
        return "redirect:/post";
    }

    @GetMapping("/edit")
    public String showEditForm(@RequestParam UUID id, Model model) {
        if (!model.containsAttribute("post")) {
            PostUpdateDto post = modelMapper.map(postService.getPostById(id), PostUpdateDto.class);
            model.addAttribute("post", post);
        }
        model.addAttribute("id", id);

        return "post/edit";
    }

    @PostMapping("/update")
    public String updatePost(@RequestParam UUID id,
                             @Valid @ModelAttribute("post") PostUpdateDto post,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.post", bindingResult);
            redirectAttributes.addFlashAttribute("post", post);
            return "redirect:/post/edit?id=" + id;
        }

        postService.updatePost(post, id);
        return "redirect:/post";
    }

    @GetMapping("/delete")
    public String deletePost(@RequestParam UUID id) {
        postService.deletePost(id);
        return "redirect:/post";
    }
}
