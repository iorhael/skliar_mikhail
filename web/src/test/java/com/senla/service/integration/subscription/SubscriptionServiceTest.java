package com.senla.service.integration.subscription;

import com.senla.dto.subscription.SubscriptionCreateDto;
import com.senla.dto.subscription.SubscriptionGetDto;
import com.senla.dto.subscription.SubscriptionUpdateDto;
import com.senla.model.Subscription;
import com.senla.model.SubscriptionPlan;
import com.senla.model.SubscriptionType;
import com.senla.model.User;
import com.senla.repository.SubscriptionPlanRepository;
import com.senla.repository.SubscriptionRepository;
import com.senla.repository.UserRepository;
import com.senla.service.SubscriptionService;
import com.senla.service.integration.common.CommonServiceTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@ContextConfiguration(classes = SubscriptionServiceTestConfiguration.class)
class SubscriptionServiceTest extends CommonServiceTest {
    static final String STRING_PLACEHOLDER = "Test";

    @Autowired
    SubscriptionService subscriptionService;

    @Autowired
    SubscriptionRepository subscriptionRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    SubscriptionPlanRepository subscriptionPlanRepository;

    @Autowired
    TestEntityManager entityManager;

    Subscription subscription;

    SubscriptionPlan subscriptionPlan;

    User user;

    @BeforeEach
    void setUp() {
        subscriptionPlan = new SubscriptionPlan();
        subscriptionPlan.setName(SubscriptionType.FREE);
        subscriptionPlan.setPricePerMonth(new BigDecimal(0));
        subscriptionPlanRepository.save(subscriptionPlan);

        user = new User();
        user.setUsername(STRING_PLACEHOLDER);
        user.setEmail(STRING_PLACEHOLDER);
        user.setPassword(STRING_PLACEHOLDER);
        userRepository.save(user);

        subscription = new Subscription();
        subscription.setExpiresDate(Instant.now().plusSeconds(30));
        subscription.setSubscriptionPlan(subscriptionPlan);
        subscription.setUser(user);
        subscriptionRepository.save(subscription);
    }

    @Test
    void whenCreateSubscription() {
        Instant expiresDate = Instant.now().plusSeconds(30);

        User user1 = new User();
        user1.setUsername("John");
        user1.setEmail("test@test.com");
        user1.setPassword(STRING_PLACEHOLDER);
        userRepository.save(user1);

        SubscriptionCreateDto createDto = new SubscriptionCreateDto();
        createDto.setSubscriptionPlanId(subscriptionPlan.getId());
        createDto.setUserId(user1.getId());
        createDto.setExpiresDate(expiresDate);

        SubscriptionGetDto dto = subscriptionService.createSubscription(createDto);

        assertNotNull(dto);
        assertEquals(user1.getUsername(), dto.getUsername());
        assertEquals(expiresDate, dto.getExpiresDate());
        assertThat(subscriptionRepository.findAll()).hasSize(2);
    }

    @Test
    void whenUpdateSubscription() {
        UUID id = subscription.getId();
        Instant expiresDate = Instant.now().plus(2, ChronoUnit.DAYS);

        SubscriptionUpdateDto updateDto = new SubscriptionUpdateDto();
        updateDto.setExpiresDate(expiresDate);

        SubscriptionGetDto dto = subscriptionService.updateSubscription(updateDto, id);

        assertNotNull(dto);
        assertEquals(expiresDate, dto.getExpiresDate());
    }

    @Test
    void whenDeleteSubscription() {
        subscriptionService.deleteSubscription(subscription.getId());

        assertTrue(subscriptionRepository.findAll().isEmpty());
    }
}
