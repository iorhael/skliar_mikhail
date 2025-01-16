package com.senla.repository;

import com.senla.di.annotation.Component;
import com.senla.model.Subscription;
import com.senla.model.SubscriptionPlan;
import com.senla.model.User;
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
    public Subscription create(Subscription subscription) {
        try (EntityManager entityManager = EntityManagerUtil.getEntityManager()) {
            entityManager.getTransaction().begin();

            User persistedUser = entityManager.getReference(User.class, subscription.getUser().getId());
            SubscriptionPlan persistedSubscriptionPlan = entityManager.getReference(SubscriptionPlan.class, subscription.getSubscriptionPlan().getId());

            subscription.setUser(persistedUser);
            subscription.setSubscriptionPlan(persistedSubscriptionPlan);

            entityManager.persist(subscription);

            entityManager.getTransaction().commit();
        }
        return subscription;
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
