package com.senla.api.service;

import com.senla.api.model.SubscriptionPlan;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SubscriptionPlanService {
    Optional<SubscriptionPlan> createSubscriptionPlan(SubscriptionPlan subscriptionPlan);

    Optional<SubscriptionPlan> getSubscriptionPlanById(UUID id);

    List<SubscriptionPlan> getAllSubscriptionPlans();

    Optional<SubscriptionPlan> updateSubscriptionPlan(SubscriptionPlan subscriptionPlan, UUID id);

    Optional<SubscriptionPlan> deleteSubscriptionPlan(UUID id);
}
