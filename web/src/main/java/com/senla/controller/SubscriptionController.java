package com.senla.controller;

import com.senla.dto.subscription.SubscriptionCreateDto;
import com.senla.dto.subscription.SubscriptionGetDto;
import com.senla.dto.subscription.SubscriptionUpdateDto;
import com.senla.service.SubscriptionPlanService;
import com.senla.service.SubscriptionService;
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
    private static final String LIST_PATH = "subscription/list";
    private static final String CREATE_PATH = "subscription/create";
    private static final String EDIT_PATH = "subscription/edit";
    private static final String LIST_REDIRECT_PATH = "redirect:/subscription";
    private static final String CREATE_REDIRECT_PATH = "redirect:/subscription/new";
    private static final String EDIT_REDIRECT_PATH = "redirect:/subscription/edit?id=";
    private static final String SUBSCRIPTION_ATTRIBUTE_NAME = "subscription";
    private static final String SUBSCRIPTIONS_ATTRIBUTE_NAME = "subscriptions";
    private static final String USERS_ATTRIBUTE_NAME = "users";
    private static final String SUBSCRIPTION_PLANS_ATTRIBUTE_NAME = "subscriptionPlans";

    private final SubscriptionService subscriptionService;

    private final UserService userService;

    private final SubscriptionPlanService subscriptionPlanService;

    private final ModelMapper modelMapper;

    @GetMapping
    public String showListForm(Model model) {
        List<SubscriptionGetDto> subscriptions = subscriptionService.getAllSubscriptions();
        model.addAttribute(SUBSCRIPTIONS_ATTRIBUTE_NAME, subscriptions);

        return LIST_PATH;
    }

    @GetMapping("/new")
    public String showNewForm(Model model) {
        if (!model.containsAttribute(SUBSCRIPTION_ATTRIBUTE_NAME)) {
            model.addAttribute(SUBSCRIPTION_ATTRIBUTE_NAME, new SubscriptionCreateDto());
        }
        model.addAttribute(USERS_ATTRIBUTE_NAME, userService.getAllUsers());
        model.addAttribute(SUBSCRIPTION_PLANS_ATTRIBUTE_NAME, subscriptionPlanService.getAllSubscriptionPlans());

        return CREATE_PATH;
    }

    @PostMapping("/insert")
    public String createSubscription(@Valid @ModelAttribute(SUBSCRIPTION_ATTRIBUTE_NAME) SubscriptionCreateDto subscription,
                                     BindingResult bindingResult,
                                     RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.subscription", bindingResult);
            redirectAttributes.addFlashAttribute(SUBSCRIPTION_ATTRIBUTE_NAME, subscription);
            return CREATE_REDIRECT_PATH;
        }

        subscriptionService.createSubscription(subscription);
        return LIST_REDIRECT_PATH;
    }

    @GetMapping("/edit")
    public String showEditForm(@RequestParam UUID id, Model model) {
        if (!model.containsAttribute(SUBSCRIPTION_ATTRIBUTE_NAME)) {
            SubscriptionUpdateDto subscription = modelMapper.map(subscriptionService.getSubscriptionById(id), SubscriptionUpdateDto.class);
            model.addAttribute(SUBSCRIPTION_ATTRIBUTE_NAME, subscription);
        }
        model.addAttribute("id", id);

        return EDIT_PATH;
    }

    @PostMapping("/update")
    public String updateSubscription(@RequestParam UUID id,
                                     @Valid @ModelAttribute(SUBSCRIPTION_ATTRIBUTE_NAME) SubscriptionUpdateDto subscription,
                                     BindingResult bindingResult,
                                     RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.subscription", bindingResult);
            redirectAttributes.addFlashAttribute(SUBSCRIPTION_ATTRIBUTE_NAME, subscription);
            return EDIT_REDIRECT_PATH + id;
        }

        subscriptionService.updateSubscription(subscription, id);
        return LIST_REDIRECT_PATH;
    }

    @GetMapping("/delete")
    public String deleteSubscription(@RequestParam UUID id) {
        subscriptionService.deleteSubscription(id);
        return LIST_REDIRECT_PATH;
    }
}
