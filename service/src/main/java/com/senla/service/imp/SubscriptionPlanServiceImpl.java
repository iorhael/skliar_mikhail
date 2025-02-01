package com.senla.service.imp;

import com.senla.dto.subscriptionPlan.SubscriptionPlanDto;
import com.senla.model.SubscriptionPlan;
import com.senla.repository.SubscriptionPlanRepository;
import com.senla.service.SubscriptionPlanService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SubscriptionPlanServiceImpl implements SubscriptionPlanService {
    public static final String SUBSCRIPTION_PLAN_NOT_FOUND = "SubscriptionPlan not found";

    private final SubscriptionPlanRepository subscriptionPlanRepository;

    private final ModelMapper modelMapper;

    @Override
    public SubscriptionPlanDto createSubscriptionPlan(SubscriptionPlanDto subscriptionPlanDto) {
        SubscriptionPlan subscriptionPlan = modelMapper.map(subscriptionPlanDto, SubscriptionPlan.class);

        subscriptionPlanRepository.save(subscriptionPlan);

        return modelMapper.map(subscriptionPlan, SubscriptionPlanDto.class);
    }

    @Override
    public SubscriptionPlanDto getSubscriptionPlanById(UUID id) {
        return subscriptionPlanRepository.findById(id)
                .map(subscriptionPlan -> modelMapper.map(subscriptionPlan, SubscriptionPlanDto.class))
                .orElseThrow(() -> new EntityNotFoundException(SUBSCRIPTION_PLAN_NOT_FOUND));
    }

    @Override
    public List<SubscriptionPlanDto> getAllSubscriptionPlans() {
        return subscriptionPlanRepository.findAll()
                .stream()
                .map(publicationStatus -> modelMapper.map(publicationStatus, SubscriptionPlanDto.class))
                .toList();
    }

    @Override
    @Transactional
    public SubscriptionPlanDto updateSubscriptionPlan(SubscriptionPlanDto subscriptionPlanDto, UUID id) {
        SubscriptionPlan subscriptionPlan = subscriptionPlanRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(SUBSCRIPTION_PLAN_NOT_FOUND));

        subscriptionPlan.setName(subscriptionPlanDto.getName());
        subscriptionPlan.setPricePerMonth(subscriptionPlanDto.getPricePerMonth());

        return modelMapper.map(subscriptionPlan, SubscriptionPlanDto.class);
    }

    @Override
    public void deleteSubscriptionPlan(UUID id) {
        subscriptionPlanRepository.deleteById(id);
    }
}
