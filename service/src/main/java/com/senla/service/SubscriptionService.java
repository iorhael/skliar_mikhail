package com.senla.service;

import com.senla.dto.subscription.SubscriptionCreateDto;
import com.senla.dto.subscription.SubscriptionGetDto;
import com.senla.dto.subscription.SubscriptionUpdateDto;

import java.util.List;
import java.util.UUID;

public interface SubscriptionService {
    SubscriptionGetDto createSubscription(SubscriptionCreateDto subscription);

    SubscriptionGetDto getSubscriptionBy(UUID id);

    List<SubscriptionGetDto> getAllSubscriptions(int pageNo, int pageSize);

    SubscriptionGetDto updateSubscription(SubscriptionUpdateDto subscription, UUID id);

    void deleteSubscription(UUID id);
}
