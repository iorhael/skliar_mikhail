package com.senla.controller;

import com.senla.dto.comment.CommentCreateDto;
import com.senla.dto.comment.CommentGetDto;
import com.senla.dto.comment.CommentUpdateDto;
import com.senla.service.CommentService;
import com.senla.service.PostService;
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
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {
    private static final String LIST_PATH = "comment/list";
    private static final String CREATE_PATH = "comment/create";
    private static final String EDIT_PATH = "comment/edit";
    private static final String LIST_REDIRECT_PATH = "redirect:/comment";
    private static final String CREATE_REDIRECT_PATH = "redirect:/comment/new";
    private static final String EDIT_REDIRECT_PATH = "redirect:/comment/edit?id=";
    private static final String COMMENT_ATTRIBUTE_NAME = "comment";
    private static final String COMMENTS_ATTRIBUTE_NAME = "comments";
    private static final String POSTS_ATTRIBUTE_NAME = "posts";
    private static final String AUTHORS_ATTRIBUTE_NAME = "authors";

    private final CommentService commentService;

    private final PostService postService;

    private final UserService userService;

    private final ModelMapper modelMapper;

    @GetMapping
    public String showListForm(Model model) {
        List<CommentGetDto> comments = commentService.getAllComments();
        model.addAttribute(COMMENT_ATTRIBUTE_NAME, comments);

        return LIST_PATH;
    }

    @GetMapping("/new")
    public String showNewForm(Model model) {
        if (!model.containsAttribute(COMMENT_ATTRIBUTE_NAME)) {
            model.addAttribute(COMMENT_ATTRIBUTE_NAME, new CommentCreateDto());
        }
        model.addAttribute(POSTS_ATTRIBUTE_NAME, postService.getAllPosts());
        model.addAttribute(AUTHORS_ATTRIBUTE_NAME, userService.getAllUsers());
        model.addAttribute(COMMENTS_ATTRIBUTE_NAME, commentService.getAllComments());

        return CREATE_PATH;
    }

    @PostMapping("/insert")
    public String createComment(@Valid @ModelAttribute(COMMENT_ATTRIBUTE_NAME) CommentCreateDto comment,
                                BindingResult bindingResult,
                                RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.comment", bindingResult);
            redirectAttributes.addFlashAttribute(COMMENTS_ATTRIBUTE_NAME, comment);
            return CREATE_REDIRECT_PATH;
        }

        if (comment.getParentComment().getId() == null) comment.setParentComment(null);

        commentService.createComment(comment);
        return LIST_REDIRECT_PATH;
    }

    @GetMapping("/edit")
    public String showEditForm(@RequestParam UUID id, Model model) {
        if (!model.containsAttribute(COMMENT_ATTRIBUTE_NAME)) {
            CommentUpdateDto comment = modelMapper.map(commentService.getCommentById(id), CommentUpdateDto.class);
            model.addAttribute(COMMENT_ATTRIBUTE_NAME, comment);
        }
        model.addAttribute("id", id);

        return EDIT_PATH;
    }

    @PostMapping("/update")
    public String updateComment(@RequestParam UUID id,
                                @Valid @ModelAttribute(COMMENT_ATTRIBUTE_NAME) CommentUpdateDto comment,
                                BindingResult bindingResult,
                                RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.comment", bindingResult);
            redirectAttributes.addFlashAttribute(COMMENT_ATTRIBUTE_NAME, comment);
            return EDIT_REDIRECT_PATH + id;
        }

        commentService.updateComment(comment, id);
        return LIST_REDIRECT_PATH;
    }

    @GetMapping("/delete")
    public String deleteComment(@RequestParam UUID id) {
        commentService.deleteComment(id);
        return LIST_REDIRECT_PATH;
    }
}
