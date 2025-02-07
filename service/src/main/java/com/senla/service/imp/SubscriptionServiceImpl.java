package com.senla.service.imp;

import com.senla.aspect.Benchmarked;
import com.senla.dto.subscription.SubscriptionCreateDto;
import com.senla.dto.subscription.SubscriptionGetDto;
import com.senla.dto.subscription.SubscriptionUpdateDto;
import com.senla.model.Subscription;
import com.senla.model.SubscriptionPlan;
import com.senla.model.User;
import com.senla.repository.SubscriptionPlanRepository;
import com.senla.repository.SubscriptionRepository;
import com.senla.repository.UserRepository;
import com.senla.service.SubscriptionService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@Benchmarked
@RequiredArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {
    public static final String SUBSCRIPTION_NOT_FOUND = "Subscription not found";

    private final SubscriptionRepository subscriptionRepository;

    private final UserRepository userRepository;

    private final SubscriptionPlanRepository subscriptionPlanRepository;

    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public SubscriptionGetDto createSubscription(SubscriptionCreateDto subscriptionCreateDto) {
        Subscription subscription = modelMapper.map(subscriptionCreateDto, Subscription.class);

        User user = userRepository.getReferenceById(subscriptionCreateDto.getUserId());
        SubscriptionPlan subscriptionPlan = subscriptionPlanRepository.getReferenceById(subscriptionCreateDto.getSubscriptionPlanId());

        subscription.setUser(user);
        subscription.setSubscriptionPlan(subscriptionPlan);

        log.info("Trying to create subscription with user id {} and subscription plan id {}",
                user.getId(),
                subscriptionPlan.getId());

        subscriptionRepository.save(subscription);

        log.info("Subscription with id {} created successfully", subscription.getId());

        return modelMapper.map(subscription, SubscriptionGetDto.class);
    }

    @Override
    public SubscriptionGetDto getSubscriptionBy(UUID id) {
        return subscriptionRepository.findWithUserById(id)
                .map(subscription -> modelMapper.map(subscription, SubscriptionGetDto.class))
                .orElseThrow(() -> new EntityNotFoundException(SUBSCRIPTION_NOT_FOUND));
    }

    @Override
    public List<SubscriptionGetDto> getAllSubscriptions(int pageNo, int pageSize) {
        return subscriptionRepository.findWithUserBy(PageRequest.of(pageNo, pageSize))
                .stream()
                .map(subscription -> modelMapper.map(subscription, SubscriptionGetDto.class))
                .toList();
    }

    @Override
    @Transactional
    public SubscriptionGetDto updateSubscription(SubscriptionUpdateDto subscriptionUpdateDto, UUID id) {
        Subscription subscription = subscriptionRepository.findWithUserById(id)
                .orElseThrow(() -> new EntityNotFoundException(SUBSCRIPTION_NOT_FOUND));

        modelMapper.map(subscriptionUpdateDto, subscription);

        return modelMapper.map(subscription, SubscriptionGetDto.class);
    }

    @Override
    public void deleteSubscription(UUID id) {
        subscriptionRepository.deleteById(id);
    }
}
