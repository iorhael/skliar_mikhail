package com.senla.service;

import com.senla.dto.subscriptionPlan.SubscriptionPlanDto;

import java.util.List;
import java.util.UUID;

public interface SubscriptionPlanService {
    SubscriptionPlanDto createSubscriptionPlan(SubscriptionPlanDto subscriptionPlan);

    SubscriptionPlanDto getSubscriptionPlanBy(UUID id);

    List<SubscriptionPlanDto> getAllSubscriptionPlans();

    SubscriptionPlanDto updateSubscriptionPlan(SubscriptionPlanDto subscriptionPlan, UUID id);

    void deleteSubscriptionPlan(UUID id);
}
