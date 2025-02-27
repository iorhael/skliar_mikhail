package com.senla.service.integration.subscription;

import com.senla.repository.SubscriptionPlanRepository;
import com.senla.repository.SubscriptionRepository;
import com.senla.repository.UserRepository;
import com.senla.service.SubscriptionService;
import com.senla.service.imp.SubscriptionServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class SubscriptionServiceTestConfiguration {

    @Bean
    SubscriptionService subscriptionService(SubscriptionRepository subscriptionRepository,
                                            UserRepository userRepository,
                                            SubscriptionPlanRepository subscriptionPlanRepository,
                                            ModelMapper modelMapper) {
        return new SubscriptionServiceImpl(
                subscriptionRepository,
                userRepository,
                subscriptionPlanRepository,
                modelMapper
        );
    }
}
