package com.senla.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.JdbcType;
import org.hibernate.dialect.PostgreSQLEnumJdbcType;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "subscription_plans")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class SubscriptionPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @JdbcType(PostgreSQLEnumJdbcType.class)
    @Column(name = "name")
    private SubscriptionType name;

    @Column(name = "price_per_month")
    private BigDecimal pricePerMonth;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "subscriptionPlan", orphanRemoval = true)
    @ToString.Exclude
    private List<Subscription> subscriptions = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "subscriptionPlan", orphanRemoval = true)
    @ToString.Exclude
    private List<Post> posts = new ArrayList<>();

    public void addPost(Post post) {
        post.setSubscriptionPlan(this);
        posts.add(post);
    }

    public void removePost(Post post) {
        posts.remove(post);
    }

    public void addSubscription(Subscription subscription) {
        subscription.setSubscriptionPlan(this);
        subscriptions.add(subscription);
    }

    public void removeSubscription(Subscription subscription) {
        subscriptions.remove(subscription);
    }
}
