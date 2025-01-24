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

    private final PollService pollService;

    private final PostService postService;

    private final UserService userService;

    private final ModelMapper modelMapper;

    @GetMapping
    public String showListForm(Model model) {
        List<PollGetDto> polls = pollService.getAllPolls();
        model.addAttribute("polls", polls);

        return "poll/list";
    }

    @GetMapping("/new")
    public String showNewForm(Model model) {
        if (!model.containsAttribute("poll")) {
            model.addAttribute("poll", new PollCreateDto());
        }
        model.addAttribute("posts", postService.getAllPosts());
        model.addAttribute("authors", userService.getAllUsers());
        model.addAttribute("polls", pollService.getAllPolls());

        return "poll/create";
    }

    @PostMapping("/insert")
    public String createPoll(@Valid @ModelAttribute("poll") PollCreateDto poll,
                                BindingResult bindingResult,
                                RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.poll", bindingResult);
            redirectAttributes.addFlashAttribute("poll", poll);
            return "redirect:/poll/new";
        }

        pollService.createPoll(poll);
        return "redirect:/poll";
    }

    @GetMapping("/edit")
    public String showEditForm(@RequestParam UUID id, Model model) {
        if (!model.containsAttribute("poll")) {
            PollUpdateDto poll = modelMapper.map(pollService.getPollById(id), PollUpdateDto.class);
            model.addAttribute("poll", poll);
        }
        model.addAttribute("id", id);

        return "poll/edit";
    }

    @PostMapping("/update")
    public String updatePoll(@RequestParam UUID id,
                             @Valid @ModelAttribute("poll") PollUpdateDto poll,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.poll", bindingResult);
            redirectAttributes.addFlashAttribute("poll", poll);
            return "redirect:/poll/edit?id=" + id;
        }

        pollService.updatePoll(poll, id);
        return "redirect:/poll";
    }

    @GetMapping("/delete")
    public String deletePoll(@RequestParam UUID id) {
        pollService.deletePoll(id);
        return "redirect:/poll";
    }
}
