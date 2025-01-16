package com.senla.service.imp;

import com.senla.di.annotation.Autowired;
import com.senla.di.annotation.Component;
import com.senla.dto.subscriptionPlan.SubscriptionPlanDto;
import com.senla.model.SubscriptionPlan;
import com.senla.repository.SubscriptionPlanRepository;
import com.senla.service.SubscriptionPlanService;
import com.senla.service.exception.ServiceException;
import com.senla.service.exception.subscriptionPlan.SubscriptionPlanDeleteException;
import com.senla.service.exception.subscriptionPlan.SubscriptionPlanUpdateException;
import com.senla.util.ModelMapperUtil;

import java.util.List;
import java.util.UUID;

@Component
public class SubscriptionPlanServiceImpl implements SubscriptionPlanService {

    @Autowired
    private SubscriptionPlanRepository subscriptionPlanRepository;

    @Override
    public SubscriptionPlanDto createSubscriptionPlan(SubscriptionPlanDto subscriptionPlan) {
        SubscriptionPlan subscriptionPlanEntity = ModelMapperUtil.MODEL_MAPPER.map(subscriptionPlan, SubscriptionPlan.class);
        SubscriptionPlan createdSubscriptionPlan = subscriptionPlanRepository.create(subscriptionPlanEntity);

        return ModelMapperUtil.MODEL_MAPPER.map(createdSubscriptionPlan, SubscriptionPlanDto.class);
    }

    @Override
    public SubscriptionPlanDto getSubscriptionPlanById(UUID id) {
        return subscriptionPlanRepository.findById(id)
                .map(subscriptionPlan -> ModelMapperUtil.MODEL_MAPPER.map(subscriptionPlan, SubscriptionPlanDto.class))
                .orElseThrow(() -> new ServiceException("No subscription plan found"));
    }

    @Override
    public List<SubscriptionPlanDto> getAllSubscriptionPlans() {
        return subscriptionPlanRepository.findAll().stream()
                .map(subscriptionPlan -> ModelMapperUtil.MODEL_MAPPER.map(subscriptionPlan, SubscriptionPlanDto.class))
                .toList();
    }

    @Override
    public SubscriptionPlanDto updateSubscriptionPlan(SubscriptionPlanDto role, UUID id) {
        SubscriptionPlan roleEntity = ModelMapperUtil.MODEL_MAPPER.map(role, SubscriptionPlan.class);

        return subscriptionPlanRepository.update(roleEntity, id)
                .map(s -> ModelMapperUtil.MODEL_MAPPER.map(s, SubscriptionPlanDto.class))
                .orElseThrow(() -> new SubscriptionPlanUpdateException("Can't update subscription plan"));
    }

    @Override
    public SubscriptionPlanDto deleteSubscriptionPlan(UUID id) {
        return subscriptionPlanRepository.deleteById(id)
                .map(pollOption -> ModelMapperUtil.MODEL_MAPPER.map(pollOption, SubscriptionPlanDto.class))
                .orElseThrow(() -> new SubscriptionPlanDeleteException("Can't delete subscription plan"));
    }
}
