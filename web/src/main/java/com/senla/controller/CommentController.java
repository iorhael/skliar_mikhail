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

    private final CommentService commentService;

    private final PostService postService;

    private final UserService userService;

    private final ModelMapper modelMapper;

    @GetMapping
    public String showListForm(Model model) {
        List<CommentGetDto> comments = commentService.getAllComments();
        model.addAttribute("comments", comments);

        return "comment/list";
    }

    @GetMapping("/new")
    public String showNewForm(Model model) {
        if (!model.containsAttribute("comment")) {
            model.addAttribute("comment", new CommentCreateDto());
        }
        model.addAttribute("posts", postService.getAllPosts());
        model.addAttribute("authors", userService.getAllUsers());
        model.addAttribute("comments", commentService.getAllComments());

        return "comment/create";
    }

    @PostMapping("/insert")
    public String createComment(@Valid @ModelAttribute("comment") CommentCreateDto comment,
                                 BindingResult bindingResult,
                                 RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.comment", bindingResult);
            redirectAttributes.addFlashAttribute("comment", comment);
            return "redirect:/comment/new";
        }

        if (comment.getParentComment().getId() == null) comment.setParentComment(null);

        commentService.createComment(comment);
        return "redirect:/comment";
    }

    @GetMapping("/edit")
    public String showEditForm(@RequestParam UUID id, Model model) {
        if (!model.containsAttribute("comment")) {
            CommentUpdateDto comment = modelMapper.map(commentService.getCommentById(id), CommentUpdateDto.class);
            model.addAttribute("comment", comment);
        }
        model.addAttribute("id", id);

        return "comment/edit";
    }

    @PostMapping("/update")
    public String updateComment(@RequestParam UUID id,
                                @Valid @ModelAttribute("comment") CommentUpdateDto comment,
                                BindingResult bindingResult,
                                RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.comment", bindingResult);
            redirectAttributes.addFlashAttribute("comment", comment);
            return "redirect:/comment/edit?id=" + id;
        }

        commentService.updateComment(comment, id);
        return "redirect:/comment";
    }

    @GetMapping("/delete")
    public String deleteComment(@RequestParam UUID id) {
        commentService.deleteComment(id);
        return "redirect:/comment";
    }
}
