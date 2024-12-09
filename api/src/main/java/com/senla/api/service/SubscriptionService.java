package com.senla.api.service;

import com.senla.api.model.Subscription;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SubscriptionService {
    Optional<Subscription> createSubscription(Subscription subscription);

    Optional<Subscription> getSubscriptionById(UUID id);

    List<Subscription> getAllSubscriptions();

    Optional<Subscription> updateSubscription(Subscription subscription, UUID id);

    Optional<Subscription> deleteSubscription(UUID id);
}
