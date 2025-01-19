package com.senla.service.imp;

import com.senla.dto.subscriptionPlan.SubscriptionPlanDto;
import com.senla.model.SubscriptionPlan;
import com.senla.repository.SubscriptionPlanRepository;
import com.senla.service.SubscriptionPlanService;
import com.senla.service.exception.ServiceException;
import com.senla.service.exception.subscriptionPlan.SubscriptionPlanDeleteException;
import com.senla.service.exception.subscriptionPlan.SubscriptionPlanUpdateException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class SubscriptionPlanServiceImpl implements SubscriptionPlanService {

    private final SubscriptionPlanRepository subscriptionPlanRepository;

    private final ModelMapper modelMapper;

    public SubscriptionPlanServiceImpl(SubscriptionPlanRepository subscriptionPlanRepository, ModelMapper modelMapper) {
        this.subscriptionPlanRepository = subscriptionPlanRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    @Override
    public SubscriptionPlanDto createSubscriptionPlan(SubscriptionPlanDto subscriptionPlan) {
        SubscriptionPlan subscriptionPlanEntity = modelMapper.map(subscriptionPlan, SubscriptionPlan.class);

        SubscriptionPlan createdSubscriptionPlan = subscriptionPlanRepository.create(subscriptionPlanEntity);

        return modelMapper.map(createdSubscriptionPlan, SubscriptionPlanDto.class);
    }

    @Transactional
    @Override
    public SubscriptionPlanDto getSubscriptionPlanById(UUID id) {
        return subscriptionPlanRepository.findById(id)
                .map(subscriptionPlan -> modelMapper.map(subscriptionPlan, SubscriptionPlanDto.class))
                .orElseThrow(() -> new ServiceException("No subscription plan found"));
    }

    @Transactional
    @Override
    public List<SubscriptionPlanDto> getAllSubscriptionPlans() {
        return subscriptionPlanRepository.findAll().stream()
                .map(subscriptionPlan -> modelMapper.map(subscriptionPlan, SubscriptionPlanDto.class))
                .toList();
    }

    @Transactional
    @Override
    public SubscriptionPlanDto updateSubscriptionPlan(SubscriptionPlanDto role, UUID id) {
        SubscriptionPlan roleEntity = modelMapper.map(role, SubscriptionPlan.class);

        return subscriptionPlanRepository.update(roleEntity, id)
                .map(s -> modelMapper.map(s, SubscriptionPlanDto.class))
                .orElseThrow(() -> new SubscriptionPlanUpdateException("Can't update subscription plan"));
    }

    @Transactional
    @Override
    public SubscriptionPlanDto deleteSubscriptionPlan(UUID id) {
        return subscriptionPlanRepository.deleteById(id)
                .map(pollOption -> modelMapper.map(pollOption, SubscriptionPlanDto.class))
                .orElseThrow(() -> new SubscriptionPlanDeleteException("Can't delete subscription plan"));
    }
}
