package com.senla.api.service.imp;

import com.senla.api.dao.SubscriptionDao;
import com.senla.api.dao.exception.SubscriptionNotFoundException;
import com.senla.api.dto.subscription.SubscriptionCreateDto;
import com.senla.api.dto.subscription.SubscriptionGetDto;
import com.senla.api.dto.subscription.SubscriptionUpdateDto;
import com.senla.api.model.Subscription;
import com.senla.api.service.SubscriptionService;
import com.senla.api.service.exception.subscription.SubscriptionCreateException;
import com.senla.api.service.exception.subscription.SubscriptionDeleteException;
import com.senla.api.service.exception.subscription.SubscriptionUpdateException;
import com.senla.api.util.ModelMapperUtil;
import com.senla.di.annotation.Autowired;
import com.senla.di.annotation.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class SubscriptionServiceImpl implements SubscriptionService {
    @Autowired
    private SubscriptionDao subscriptionDao;

    @Override
    public SubscriptionGetDto createSubscription(SubscriptionCreateDto subscription) {
        Subscription subscriptionEntity = ModelMapperUtil.getConfiguredMapper().map(subscription, Subscription.class);

        return subscriptionDao.create(subscriptionEntity)
                .map(s -> ModelMapperUtil.getConfiguredMapper().map(s, SubscriptionGetDto.class))
                .orElseThrow(() -> new SubscriptionCreateException("Can't create subscription"));
    }

    @Override
    public SubscriptionGetDto getSubscriptionById(UUID id) {
        return subscriptionDao.getById(id)
                .map(subscription -> ModelMapperUtil.getConfiguredMapper().map(subscription, SubscriptionGetDto.class))
                .orElseThrow(() -> new SubscriptionNotFoundException("Subscription not found"));
    }

    @Override
    public List<SubscriptionGetDto> getAllSubscriptions() {
        List<Subscription> subscriptions = subscriptionDao.getAll();
        List<SubscriptionGetDto> subscriptionGetDtos = new ArrayList<>();

        for (Subscription subscription : subscriptions) {
            SubscriptionGetDto subscriptionGetDto = ModelMapperUtil.getConfiguredMapper().map(subscription, SubscriptionGetDto.class);
            subscriptionGetDtos.add(subscriptionGetDto);
        }

        return subscriptionGetDtos;
    }

    @Override
    public SubscriptionGetDto updateSubscription(SubscriptionUpdateDto subscription, UUID id) {
        Subscription subscriptionEntity = ModelMapperUtil.getConfiguredMapper().map(subscription, Subscription.class);

        return subscriptionDao.update(subscriptionEntity, id)
                .map(s -> ModelMapperUtil.getConfiguredMapper().map(s, SubscriptionGetDto.class))
                .orElseThrow(() -> new SubscriptionUpdateException("Can't update subscription"));
    }

    @Override
    public SubscriptionGetDto deleteSubscription(UUID id) {
        return subscriptionDao.delete(id)
                .map(subscription -> ModelMapperUtil.getConfiguredMapper().map(subscription, SubscriptionGetDto.class))
                .orElseThrow(() -> new SubscriptionDeleteException("Can't delete subscription"));
    }
}
