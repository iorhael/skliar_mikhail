package com.senla.api.service.imp;

import com.senla.api.dao.SubscriptionPlanDao;
import com.senla.api.model.SubscriptionPlan;
import com.senla.api.service.SubscriptionPlanService;
import com.senla.di.annotation.Autowired;
import com.senla.di.annotation.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class SubscriptionPlanServiceImpl implements SubscriptionPlanService {
    @Autowired
    private SubscriptionPlanDao subscriptionPlanDao;

    @Override
    public Optional<SubscriptionPlan> createSubscriptionPlan(SubscriptionPlan subscriptionPlan) {
        return subscriptionPlanDao.create(subscriptionPlan);
    }

    @Override
    public Optional<SubscriptionPlan> getSubscriptionPlanById(UUID id) {
        return subscriptionPlanDao.getById(id);
    }

    @Override
    public List<SubscriptionPlan> getAllSubscriptionPlans() {
        return subscriptionPlanDao.getAll();
    }

    @Override
    public Optional<SubscriptionPlan> updateSubscriptionPlan(SubscriptionPlan subscriptionPlan, UUID id) {
        return subscriptionPlanDao.update(subscriptionPlan, id);
    }

    @Override
    public Optional<SubscriptionPlan> deleteSubscriptionPlan(UUID id) {
        return subscriptionPlanDao.delete(id);
    }
}
