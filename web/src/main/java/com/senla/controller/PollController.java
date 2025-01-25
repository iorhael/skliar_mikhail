package com.senla.controller;

import com.senla.dto.poll.PollCreateDto;
import com.senla.dto.poll.PollGetDto;
import com.senla.dto.poll.PollUpdateDto;
import com.senla.service.PollService;
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
@RequestMapping("/poll")
@RequiredArgsConstructor
public class PollController {
    private static final String LIST_PATH = "poll/list";
    private static final String CREATE_PATH = "poll/create";
    private static final String EDIT_PATH = "poll/edit";
    private static final String LIST_REDIRECT_PATH = "redirect:/poll";
    private static final String CREATE_REDIRECT_PATH = "redirect:/poll/new";
    private static final String EDIT_REDIRECT_PATH = "redirect:/poll/edit?id=";
    private static final String POLL_ATTRIBUTE_NAME = "poll";
    private static final String POLLS_ATTRIBUTE_NAME = "polls";
    private static final String POSTS_ATTRIBUTE_NAME = "posts";
    private static final String AUTHORS_ATTRIBUTE_NAME = "authors";

    private final PollService pollService;

    private final PostService postService;

    private final UserService userService;

    private final ModelMapper modelMapper;

    @GetMapping
    public String showListForm(Model model) {
        List<PollGetDto> polls = pollService.getAllPolls();
        model.addAttribute(POLLS_ATTRIBUTE_NAME, polls);

        return LIST_PATH;
    }

    @GetMapping("/new")
    public String showNewForm(Model model) {
        if (!model.containsAttribute(POLL_ATTRIBUTE_NAME)) {
            model.addAttribute(POLL_ATTRIBUTE_NAME, new PollCreateDto());
        }
        model.addAttribute(POSTS_ATTRIBUTE_NAME, postService.getAllPosts());
        model.addAttribute(AUTHORS_ATTRIBUTE_NAME, userService.getAllUsers());
        model.addAttribute(POLLS_ATTRIBUTE_NAME, pollService.getAllPolls());

        return CREATE_PATH;
    }

    @PostMapping("/insert")
    public String createPoll(@Valid @ModelAttribute(POLL_ATTRIBUTE_NAME) PollCreateDto poll,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.poll", bindingResult);
            redirectAttributes.addFlashAttribute(POLL_ATTRIBUTE_NAME, poll);
            return CREATE_REDIRECT_PATH;
        }

        pollService.createPoll(poll);
        return LIST_REDIRECT_PATH;
    }

    @GetMapping("/edit")
    public String showEditForm(@RequestParam UUID id, Model model) {
        if (!model.containsAttribute(POLL_ATTRIBUTE_NAME)) {
            PollUpdateDto poll = modelMapper.map(pollService.getPollById(id), PollUpdateDto.class);
            model.addAttribute(POLL_ATTRIBUTE_NAME, poll);
        }
        model.addAttribute("id", id);

        return EDIT_PATH;
    }

    @PostMapping("/update")
    public String updatePoll(@RequestParam UUID id,
                             @Valid @ModelAttribute(POLL_ATTRIBUTE_NAME) PollUpdateDto poll,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.poll", bindingResult);
            redirectAttributes.addFlashAttribute(POLL_ATTRIBUTE_NAME, poll);
            return EDIT_REDIRECT_PATH + id;
        }

        pollService.updatePoll(poll, id);
        return LIST_REDIRECT_PATH;
    }

    @GetMapping("/delete")
    public String deletePoll(@RequestParam UUID id) {
        pollService.deletePoll(id);
        return LIST_REDIRECT_PATH;
    }
}
