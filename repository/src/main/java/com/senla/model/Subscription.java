package com.senla.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.NamedAttributeNode;
import jakarta.persistence.NamedEntityGraph;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "subscriptions")
@NamedEntityGraph(name = "subscription-with-user",
        attributeNodes = @NamedAttributeNode("user")
)
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Subscription {

    @Id
    private UUID id;

    @Column(name = "started_date")
    @CreationTimestamp
    private Instant startedDate;

    @Column(name = "expires_date")
    private Instant expiresDate;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "id")
    @ToString.Exclude
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subscription_plan_id")
    @ToString.Exclude
    private SubscriptionPlan subscriptionPlan;
}
