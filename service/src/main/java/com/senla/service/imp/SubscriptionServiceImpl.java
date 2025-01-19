package com.senla.service.imp;

import com.senla.dto.subscription.SubscriptionCreateDto;
import com.senla.dto.subscription.SubscriptionGetDto;
import com.senla.dto.subscription.SubscriptionUpdateDto;
import com.senla.model.Subscription;
import com.senla.repository.SubscriptionRepository;
import com.senla.service.SubscriptionService;
import com.senla.service.exception.ServiceException;
import com.senla.service.exception.subscription.SubscriptionDeleteException;
import com.senla.service.exception.subscription.SubscriptionUpdateException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;

    private final ModelMapper modelMapper;

    public SubscriptionServiceImpl(SubscriptionRepository subscriptionRepository, ModelMapper modelMapper) {
        this.subscriptionRepository = subscriptionRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    @Override
    public SubscriptionGetDto createSubscription(SubscriptionCreateDto subscription) {
        Subscription subscriptionEntity = modelMapper.map(subscription, Subscription.class);

        Subscription createdSubscription = subscriptionRepository.create(subscriptionEntity);

        return modelMapper.map(createdSubscription, SubscriptionGetDto.class);
    }

    @Transactional
    @Override
    public SubscriptionGetDto getSubscriptionById(UUID id) {
        return subscriptionRepository.findById(id)
                .map(subscription -> modelMapper.map(subscription, SubscriptionGetDto.class))
                .orElseThrow(() -> new ServiceException("Subscription not found"));
    }

    @Transactional
    @Override
    public List<SubscriptionGetDto> getAllSubscriptions() {
        return subscriptionRepository.findAll().stream()
                .map(subscription -> modelMapper.map(subscription, SubscriptionGetDto.class))
                .toList();
    }

    @Transactional
    @Override
    public SubscriptionGetDto updateSubscription(SubscriptionUpdateDto subscription, UUID id) {
        Subscription subscriptionEntity = modelMapper.map(subscription, Subscription.class);

        return subscriptionRepository.update(subscriptionEntity, id)
                .map(s -> modelMapper.map(s, SubscriptionGetDto.class))
                .orElseThrow(() -> new SubscriptionUpdateException("Can't update subscription"));
    }

    @Transactional
    @Override
    public SubscriptionGetDto deleteSubscription(UUID id) {
        return subscriptionRepository.deleteById(id)
                .map(subscription -> modelMapper.map(subscription, SubscriptionGetDto.class))
                .orElseThrow(() -> new SubscriptionDeleteException("Can't delete subscription"));
    }
}
