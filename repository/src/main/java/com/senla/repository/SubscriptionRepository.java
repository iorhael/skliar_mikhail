package com.senla.repository;

import com.senla.model.Subscription;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SubscriptionRepository extends JpaRepository<Subscription, UUID> {

    @EntityGraph("subscription-with-user")
    Optional<Subscription> findWithUserById(UUID uuid);

    @EntityGraph("subscription-with-user")
    List<Subscription> findWithUserBy(Pageable pageable);
}
