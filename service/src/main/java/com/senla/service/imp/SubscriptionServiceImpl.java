package com.senla.service.imp;

import com.senla.di.annotation.Autowired;
import com.senla.di.annotation.Component;
import com.senla.dto.subscription.SubscriptionCreateDto;
import com.senla.dto.subscription.SubscriptionGetDto;
import com.senla.dto.subscription.SubscriptionUpdateDto;
import com.senla.model.Subscription;
import com.senla.repository.SubscriptionRepository;
import com.senla.service.SubscriptionService;
import com.senla.service.exception.ServiceException;
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
        Subscription createdSubscription = subscriptionRepository.create(subscriptionEntity);

        return ModelMapperUtil.MODEL_MAPPER.map(createdSubscription, SubscriptionGetDto.class);
    }

    @Override
    public SubscriptionGetDto getSubscriptionById(UUID id) {
        return subscriptionRepository.findById(id)
                .map(subscription -> ModelMapperUtil.MODEL_MAPPER.map(subscription, SubscriptionGetDto.class))
                .orElseThrow(() -> new ServiceException("Subscription not found"));
    }

    @Override
    public List<SubscriptionGetDto> getAllSubscriptions() {
        return subscriptionRepository.findAll().stream()
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
        return subscriptionRepository.deleteById(id)
                .map(subscription -> ModelMapperUtil.MODEL_MAPPER.map(subscription, SubscriptionGetDto.class))
                .orElseThrow(() -> new SubscriptionDeleteException("Can't delete subscription"));
    }
}
