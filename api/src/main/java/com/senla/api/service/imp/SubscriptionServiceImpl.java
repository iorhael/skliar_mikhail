package com.senla.api.service.imp;

import com.senla.api.dao.SubscriptionDao;
import com.senla.api.model.Subscription;
import com.senla.api.service.SubscriptionService;
import com.senla.di.annotation.Autowired;
import com.senla.di.annotation.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class SubscriptionServiceImpl implements SubscriptionService {
    private SubscriptionDao subscriptionDao;

    @Autowired
    public void setSubscriptionDao(SubscriptionDao subscriptionDao) {
        this.subscriptionDao = subscriptionDao;
    }

    @Override
    public Optional<Subscription> createSubscription(Subscription subscription) {
        return subscriptionDao.create(subscription);
    }

    @Override
    public Optional<Subscription> getSubscriptionById(UUID id) {
        return subscriptionDao.getById(id);
    }

    @Override
    public List<Subscription> getAllSubscriptions() {
        return subscriptionDao.getAll();
    }

    @Override
    public Optional<Subscription> updateSubscription(Subscription subscription, UUID id) {
        return subscriptionDao.update(subscription, id);
    }

    @Override
    public Optional<Subscription> deleteSubscription(UUID id) {
        return subscriptionDao.delete(id);
    }
}
