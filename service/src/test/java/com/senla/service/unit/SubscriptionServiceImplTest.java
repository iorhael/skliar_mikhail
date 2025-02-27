package com.senla.service.unit;

import com.senla.dto.subscription.SubscriptionCreateDto;
import com.senla.dto.subscription.SubscriptionGetDto;
import com.senla.dto.subscription.SubscriptionUpdateDto;
import com.senla.model.Subscription;
import com.senla.model.SubscriptionPlan;
import com.senla.model.User;
import com.senla.repository.SubscriptionPlanRepository;
import com.senla.repository.SubscriptionRepository;
import com.senla.repository.UserRepository;
import com.senla.service.imp.SubscriptionServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SubscriptionServiceImplTest {

    @Mock
    SubscriptionRepository subscriptionRepository;

    @Mock
    UserRepository userRepository;

    @Mock
    SubscriptionPlanRepository subscriptionPlanRepository;

    @Mock
    ModelMapper modelMapper;

    @InjectMocks
    SubscriptionServiceImpl roleService;

    Subscription subscription;

    @BeforeEach
    void setup() {
        subscription = new Subscription();
        subscription.setId(UUID.randomUUID());
    }

    @Test
    void whenCreateSubscription_thenReturnSubscriptionGetDto() {
        SubscriptionCreateDto createDto = new SubscriptionCreateDto();
        createDto.setUserId(UUID.randomUUID());
        createDto.setSubscriptionPlanId(UUID.randomUUID());

        given(modelMapper.map(createDto, Subscription.class))
                .willReturn(subscription);
        given(userRepository.getReferenceById(createDto.getUserId()))
                .willReturn(new User());
        given(subscriptionPlanRepository.getReferenceById(createDto.getSubscriptionPlanId()))
                .willReturn(new SubscriptionPlan());
        given(modelMapper.map(subscription, SubscriptionGetDto.class))
                .willReturn(new SubscriptionGetDto());

        assertThat(roleService.createSubscription(createDto))
                .isInstanceOf(SubscriptionGetDto.class);
        assertNotNull(subscription.getUser());
        assertNotNull(subscription.getSubscriptionPlan());
    }

    @Test
    void givenSubscription_whenGetSubscriptionById_thenReturnSubscriptionGetDto() {
        UUID id = subscription.getId();

        given(subscriptionRepository.findWithUserById(id))
                .willReturn(Optional.of(subscription));
        given(modelMapper.map(subscription, SubscriptionGetDto.class))
                .willReturn(new SubscriptionGetDto());

        assertThat(roleService.getSubscriptionBy(id))
                .isInstanceOf(SubscriptionGetDto.class);
    }

    @Test
    void givenNoSubscription_whenGetSubscriptionById_thenThrowEntityNotFoundException() {
        UUID id = subscription.getId();

        given(subscriptionRepository.findWithUserById(id))
                .willReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class,
                () -> roleService.getSubscriptionBy(id));
        verify(modelMapper, never()).map(any(Subscription.class), eq(SubscriptionGetDto.class));
    }


    @Test
    void givenListOfSubscriptions_whenGetAllSubscriptions_thenReturnListOfSubscriptionGetDto() {
        Subscription subscription1 = new Subscription();
        List<Subscription> subscriptions = List.of(subscription, subscription1);

        PageRequest testPage = PageRequest.of(1, 1);

        given(subscriptionRepository.findWithUserBy(testPage))
                .willReturn(subscriptions);
        given(modelMapper.map(any(Subscription.class), eq(SubscriptionGetDto.class)))
                .willReturn(new SubscriptionGetDto());

        assertThat(roleService.getAllSubscriptions(1, 1))
                .hasSize(subscriptions.size());
    }

    @Test
    void givenSubscription_whenUpdateSubscription_thenReturnSubscriptionGetDto() {
        SubscriptionUpdateDto updateDto = new SubscriptionUpdateDto();
        UUID id = subscription.getId();

        given(subscriptionRepository.findWithUserById(id))
                .willReturn(Optional.of(subscription));
        doNothing().when(modelMapper).map(updateDto, subscription);
        given(modelMapper.map(subscription, SubscriptionGetDto.class))
                .willReturn(new SubscriptionGetDto());

        assertThat(roleService.updateSubscription(updateDto, id))
                .isInstanceOf(SubscriptionGetDto.class);
    }

    @Test
    void givenNoSubscription_whenUpdateSubscription_thenThrowEntityNotFoundException() {
        SubscriptionUpdateDto updateDto = new SubscriptionUpdateDto();
        UUID id = subscription.getId();

        given(subscriptionRepository.findWithUserById(id))
                .willReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class,
                () -> roleService.updateSubscription(updateDto, id));
    }

    @Test
    void whenDeleteSubscription() {
        UUID id = subscription.getId();

        roleService.deleteSubscription(id);

        verify(subscriptionRepository, times(1)).deleteById(id);

    }
}
