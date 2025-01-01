package com.senla.service.imp;

import com.senla.di.annotation.Autowired;
import com.senla.di.annotation.Component;
import com.senla.dto.subscription.SubscriptionCreateDto;
import com.senla.dto.subscription.SubscriptionGetDto;
import com.senla.dto.subscription.SubscriptionUpdateDto;
import com.senla.model.Subscription;
import com.senla.repository.SubscriptionRepository;
import com.senla.repository.exception.SubscriptionNotFoundException;
import com.senla.service.SubscriptionService;
import com.senla.service.exception.subscription.SubscriptionCreateException;
import com.senla.service.exception.subscription.SubscriptionDeleteException;
import com.senla.service.exception.subscription.SubscriptionUpdateException;
import com.senla.util.ModelMapperUtil;

import java.util.List;
import java.util.UUID;

@Component
public class SubscriptionServiceImpl implements SubscriptionService {
    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Override
    public SubscriptionGetDto createSubscription(SubscriptionCreateDto subscription) {
        Subscription subscriptionEntity = ModelMapperUtil.MODEL_MAPPER.map(subscription, Subscription.class);

        return subscriptionRepository.create(subscriptionEntity)
                .map(s -> ModelMapperUtil.MODEL_MAPPER.map(s, SubscriptionGetDto.class))
                .orElseThrow(() -> new SubscriptionCreateException("Can't create subscription"));
    }

    @Override
    public SubscriptionGetDto getSubscriptionById(UUID id) {
        return subscriptionRepository.getById(id)
                .map(subscription -> ModelMapperUtil.MODEL_MAPPER.map(subscription, SubscriptionGetDto.class))
                .orElseThrow(() -> new SubscriptionNotFoundException("Subscription not found"));
    }

    @Override
    public List<SubscriptionGetDto> getAllSubscriptions() {
        return subscriptionRepository.getAll().stream()
                .map(subscription -> ModelMapperUtil.MODEL_MAPPER.map(subscription, SubscriptionGetDto.class))
                .toList();
    }

    @Override
    public SubscriptionGetDto updateSubscription(SubscriptionUpdateDto subscription, UUID id) {
        Subscription subscriptionEntity = ModelMapperUtil.MODEL_MAPPER.map(subscription, Subscription.class);

        return subscriptionRepository.update(subscriptionEntity, id)
                .map(s -> ModelMapperUtil.MODEL_MAPPER.map(s, SubscriptionGetDto.class))
                .orElseThrow(() -> new SubscriptionUpdateException("Can't update subscription"));
    }

    @Override
    public SubscriptionGetDto deleteSubscription(UUID id) {
        return subscriptionRepository.delete(id)
                .map(subscription -> ModelMapperUtil.MODEL_MAPPER.map(subscription, SubscriptionGetDto.class))
                .orElseThrow(() -> new SubscriptionDeleteException("Can't delete subscription"));
    }
}
