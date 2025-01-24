package com.senla.controller;

import com.senla.dto.subscription.SubscriptionCreateDto;
import com.senla.dto.subscription.SubscriptionGetDto;
import com.senla.dto.subscription.SubscriptionUpdateDto;
import com.senla.service.SubscriptionService;
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
@RequestMapping("/subscription")
@RequiredArgsConstructor
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    private final UserService userService;

    private final SubscriptionPlanService subscriptionPlanService;

    private final ModelMapper modelMapper;

    @GetMapping
    public String showListForm(Model model) {
        List<SubscriptionGetDto> subscriptions = subscriptionService.getAllSubscriptions();
        model.addAttribute("subscriptions", subscriptions);

        return "subscription/list";
    }

    @GetMapping("/new")
    public String showNewForm(Model model) {
        if (!model.containsAttribute("subscription")) {
            model.addAttribute("subscription", new SubscriptionCreateDto());
        }
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("subscriptionPlans", subscriptionPlanService.getAllSubscriptionPlans());

        return "subscription/create";
    }

    @PostMapping("/insert")
    public String createSubscription(@Valid @ModelAttribute("subscription") SubscriptionCreateDto subscription,
                                     BindingResult bindingResult,
                                     RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.subscription", bindingResult);
            redirectAttributes.addFlashAttribute("subscription", subscription);
            return "redirect:/subscription/new";
        }

        subscriptionService.createSubscription(subscription);
        return "redirect:/subscription";
    }

    @GetMapping("/edit")
    public String showEditForm(@RequestParam UUID id, Model model) {
        if (!model.containsAttribute("subscription")) {
            SubscriptionUpdateDto subscription = modelMapper.map(subscriptionService.getSubscriptionById(id), SubscriptionUpdateDto.class);
            model.addAttribute("subscription", subscription);
        }
        model.addAttribute("id", id);

        return "subscription/edit";
    }

    @PostMapping("/update")
    public String updateSubscription(@RequestParam UUID id,
                                     @Valid @ModelAttribute("subscription") SubscriptionUpdateDto subscription,
                                     BindingResult bindingResult,
                                     RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.subscription", bindingResult);
            redirectAttributes.addFlashAttribute("subscription", subscription);
            return "redirect:/subscription/edit?id=" + id;
        }

        subscriptionService.updateSubscription(subscription, id);
        return "redirect:/subscription";
    }

    @GetMapping("/delete")
    public String deleteSubscription(@RequestParam UUID id) {
        subscriptionService.deleteSubscription(id);
        return "redirect:/subscription";
    }
}