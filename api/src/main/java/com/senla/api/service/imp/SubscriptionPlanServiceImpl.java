package com.senla.api.service.imp;

import com.senla.api.dao.SubscriptionPlanDao;
import com.senla.api.dao.exception.SubscriptionPlanNotFoundException;
import com.senla.api.model.SubscriptionPlan;
import com.senla.api.service.SubscriptionPlanService;
import com.senla.api.service.exception.subscriptionPlan.SubscriptionPlanCreateException;
import com.senla.api.service.exception.subscriptionPlan.SubscriptionPlanDeleteException;
import com.senla.api.service.exception.subscriptionPlan.SubscriptionPlanUpdateException;
import com.senla.di.annotation.Autowired;
import com.senla.di.annotation.Component;

import java.util.List;
import java.util.UUID;

@Component
public class SubscriptionPlanServiceImpl implements SubscriptionPlanService {
    @Autowired
    private SubscriptionPlanDao subscriptionPlanDao;

    @Override
    public SubscriptionPlan createSubscriptionPlan(SubscriptionPlan subscriptionPlan) {
        return subscriptionPlanDao.create(subscriptionPlan).orElseThrow(() -> new SubscriptionPlanCreateException("Can't create subscription plan"));
    }

    @Override
    public SubscriptionPlan getSubscriptionPlanById(UUID id) {
        return subscriptionPlanDao.getById(id).orElseThrow(() -> new SubscriptionPlanNotFoundException("No subscription plan found"));
    }

    @Override
    public List<SubscriptionPlan> getAllSubscriptionPlans() {
        return subscriptionPlanDao.getAll();
    }

    @Override
    public SubscriptionPlan updateSubscriptionPlan(SubscriptionPlan subscriptionPlan, UUID id) {
        return subscriptionPlanDao.update(subscriptionPlan, id).orElseThrow(() -> new SubscriptionPlanUpdateException("Can't update subscription plan"));
    }

    @Override
    public SubscriptionPlan deleteSubscriptionPlan(UUID id) {
        return subscriptionPlanDao.delete(id).orElseThrow(() -> new SubscriptionPlanDeleteException("Can't delete subscription plan"));
    }
}
