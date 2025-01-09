package com.senla.repository;

import com.senla.di.annotation.Component;
import com.senla.model.SubscriptionPlan;
import com.senla.util.SessionManager;
import org.hibernate.Session;

import java.util.Optional;
import java.util.UUID;

@Component
public class SubscriptionPlanRepository extends BaseRepository<SubscriptionPlan, UUID> {
    public SubscriptionPlanRepository() {
        super(SubscriptionPlan.class);
    }

    @Override
    public Optional<SubscriptionPlan> update(SubscriptionPlan subscriptionPlan, UUID id) {
        Optional<SubscriptionPlan> result = Optional.empty();

        try (Session session = SessionManager.openSession()) {
            session.beginTransaction();

            SubscriptionPlan existingSubscriptionPlan = session.get(SubscriptionPlan.class, id);

            if (existingSubscriptionPlan != null) {
                existingSubscriptionPlan.setName(subscriptionPlan.getName());
                existingSubscriptionPlan.setPricePerMonth(subscriptionPlan.getPricePerMonth());

                result = Optional.of(existingSubscriptionPlan);
            }

            session.getTransaction().commit();
        }
        return result;
    }
}