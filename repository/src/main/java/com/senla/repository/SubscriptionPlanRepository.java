package com.senla.repository;

import com.senla.di.annotation.Component;
import com.senla.model.SubscriptionPlan;
import com.senla.util.EntityManagerUtil;
import jakarta.persistence.EntityManager;

import java.util.Optional;
import java.util.UUID;

@Component
public class SubscriptionPlanRepository extends BaseRepository<SubscriptionPlan, UUID> {
    public SubscriptionPlanRepository() {
        super(SubscriptionPlan.class);
    }

    @Override
    public Optional<SubscriptionPlan> update(SubscriptionPlan subscriptionPlan, UUID id) {
        try (EntityManager entityManager = EntityManagerUtil.getEntityManager()) {
            entityManager.getTransaction().begin();

            SubscriptionPlan existingSubscriptionPlan = entityManager.find(SubscriptionPlan.class, id);

            if (existingSubscriptionPlan != null) {
                existingSubscriptionPlan.setName(subscriptionPlan.getName());
                existingSubscriptionPlan.setPricePerMonth(subscriptionPlan.getPricePerMonth());
            }

            entityManager.getTransaction().commit();

            return Optional.ofNullable(existingSubscriptionPlan);
        }
    }
}
