package com.senla.repository;

import com.senla.model.SubscriptionPlan;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class SubscriptionPlanRepository extends BaseRepository<SubscriptionPlan, UUID> {

    public SubscriptionPlanRepository() {
        super(SubscriptionPlan.class);
    }

    @Override
    public Optional<SubscriptionPlan> update(SubscriptionPlan subscriptionPlan, UUID id) {
        SubscriptionPlan existingSubscriptionPlan = entityManager.find(SubscriptionPlan.class, id);

        if (existingSubscriptionPlan != null) {
            existingSubscriptionPlan.setName(subscriptionPlan.getName());
            existingSubscriptionPlan.setPricePerMonth(subscriptionPlan.getPricePerMonth());
        }

        return Optional.ofNullable(existingSubscriptionPlan);
    }
}
