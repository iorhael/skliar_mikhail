package com.senla.controller;

import com.senla.controller.dto.ResponseInfoDto;
import com.senla.dto.subscription.SubscriptionCreateDto;
import com.senla.dto.subscription.SubscriptionGetDto;
import com.senla.dto.subscription.SubscriptionUpdateDto;
import com.senla.service.SubscriptionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/subscriptions")
@RequiredArgsConstructor
public class SubscriptionController {
    private static final String SUBSCRIPTION_DELETION_MESSAGE = "Subscription deleted successfully";

    private final SubscriptionService subscriptionService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<SubscriptionGetDto> findAllSubscriptions() {
        return subscriptionService.getAllSubscriptions();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public SubscriptionGetDto findSubscriptionById(@PathVariable UUID id) {
        return subscriptionService.getSubscriptionById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SubscriptionGetDto createSubscription(@Valid @RequestBody SubscriptionCreateDto subscription) {
        return subscriptionService.createSubscription(subscription);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public SubscriptionGetDto updateSubscription(@Valid @RequestBody SubscriptionUpdateDto subscription,
                                                 @PathVariable UUID id) {
        return subscriptionService.updateSubscription(subscription, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseInfoDto deleteSubscription(@PathVariable UUID id) {
        subscriptionService.deleteSubscription(id);

        return ResponseInfoDto.builder()
                .message(SUBSCRIPTION_DELETION_MESSAGE)
                .build();
    }
}
