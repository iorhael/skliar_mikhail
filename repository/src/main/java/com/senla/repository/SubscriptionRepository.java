package com.senla.repository;

import com.senla.di.annotation.Component;
import com.senla.model.Subscription;
import com.senla.util.SessionManager;
import org.hibernate.Session;

import java.util.Optional;
import java.util.UUID;

@Component
public class SubscriptionRepository extends BaseRepository<Subscription, UUID> {
    public SubscriptionRepository() {
        super(Subscription.class);
    }

    @Override
    public Optional<Subscription> update(Subscription subscription, UUID id) {
        Optional<Subscription> result = Optional.empty();

        try (Session session = SessionManager.openSession()) {
            session.beginTransaction();

            Subscription existingSubscription = session.get(Subscription.class, id);

            if (existingSubscription != null) {
                existingSubscription.setExpiresDate(subscription.getExpiresDate());

                result = Optional.of(existingSubscription);
            }

            session.getTransaction().commit();
        }
        return result;
    }
}
