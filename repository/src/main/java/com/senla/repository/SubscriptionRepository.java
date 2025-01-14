package com.senla.repository;

import com.senla.di.annotation.Component;
import com.senla.model.Subscription;
import com.senla.util.EntityManagerUtil;
import jakarta.persistence.EntityManager;

import java.util.Optional;
import java.util.UUID;

@Component
public class SubscriptionRepository extends BaseRepository<Subscription, UUID> {
    public SubscriptionRepository() {
        super(Subscription.class);
    }

    @Override
    public Optional<Subscription> update(Subscription subscription, UUID id) {
        try (EntityManager entityManager = EntityManagerUtil.getEntityManager()) {
            entityManager.getTransaction().begin();

            Subscription existingSubscription = entityManager.find(Subscription.class, id);

            if (existingSubscription != null) {
                existingSubscription.setExpiresDate(subscription.getExpiresDate());
            }

            entityManager.getTransaction().commit();

            return Optional.ofNullable(subscription);
        }
    }
}
