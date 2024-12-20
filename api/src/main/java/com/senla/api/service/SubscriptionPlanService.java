package com.senla.api.service;

import com.senla.api.model.SubscriptionPlan;

import java.util.List;
import java.util.UUID;

public interface SubscriptionPlanService {
    SubscriptionPlan createSubscriptionPlan(SubscriptionPlan subscriptionPlan);

    SubscriptionPlan getSubscriptionPlanById(UUID id);

    List<SubscriptionPlan> getAllSubscriptionPlans();

    SubscriptionPlan updateSubscriptionPlan(SubscriptionPlan subscriptionPlan, UUID id);

    SubscriptionPlan deleteSubscriptionPlan(UUID id);
}
