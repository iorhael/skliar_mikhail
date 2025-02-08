package com.senla.config.modelmapper;

import com.senla.dto.subscription.SubscriptionGetDto;
import com.senla.model.Subscription;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class SubscriptionMappingsConfigurator implements ModelMapperConfigurator {

    @Override
    public void configure(ModelMapper modelMapper) {
        modelMapper.createTypeMap(Subscription.class, SubscriptionGetDto.class).addMappings(
                mapper -> mapper.map(src -> src.getUser().getUsername(), SubscriptionGetDto::setUsername)
        );
    }
}
