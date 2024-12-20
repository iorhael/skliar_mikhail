package com.senla.api.service;

import com.senla.api.dto.subscription.SubscriptionCreateDto;
import com.senla.api.dto.subscription.SubscriptionGetDto;
import com.senla.api.dto.subscription.SubscriptionUpdateDto;

import java.util.List;
import java.util.UUID;

public interface SubscriptionService {
    SubscriptionGetDto createSubscription(SubscriptionCreateDto subscription);

    SubscriptionGetDto getSubscriptionById(UUID id);

    List<SubscriptionGetDto> getAllSubscriptions();

    SubscriptionGetDto updateSubscription(SubscriptionUpdateDto subscription, UUID id);

    SubscriptionGetDto deleteSubscription(UUID id);
}
