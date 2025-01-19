package com.senla.repository;

import com.senla.model.Subscription;
import com.senla.model.SubscriptionPlan;
import com.senla.model.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class SubscriptionRepository extends BaseRepository<Subscription, UUID> {

    public SubscriptionRepository() {
        super(Subscription.class);
    }

    @Override
    public Subscription create(Subscription subscription) {
        User persistedUser = entityManager.getReference(User.class, subscription.getUser().getId());
        SubscriptionPlan persistedSubscriptionPlan = entityManager.getReference(SubscriptionPlan.class, subscription.getSubscriptionPlan().getId());

        subscription.setUser(persistedUser);
        subscription.setSubscriptionPlan(persistedSubscriptionPlan);

        entityManager.persist(subscription);

        return subscription;
    }

    @Override
    public Optional<Subscription> update(Subscription subscription, UUID id) {
        Subscription existingSubscription = entityManager.find(Subscription.class, id);

        if (existingSubscription != null) {
            existingSubscription.setExpiresDate(subscription.getExpiresDate());
        }

        return Optional.ofNullable(subscription);
    }
}
