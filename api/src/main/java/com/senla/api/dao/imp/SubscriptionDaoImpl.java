package com.senla.api.dao.imp;

import com.senla.api.dao.SubscriptionDao;
import com.senla.api.dao.exception.NoSubscriptionsFoundException;
import com.senla.api.dao.exception.SubscriptionNotFoundException;
import com.senla.api.dao.query.SubscriptionQueries;
import com.senla.api.model.Subscription;
import com.senla.api.util.ConnectionManager;
import com.senla.di.annotation.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class SubscriptionDaoImpl implements SubscriptionDao {
    @Override
    public Optional<Subscription> create(Subscription subscription) {
        try (Connection connection = ConnectionManager.open(); PreparedStatement preparedStatement = connection.prepareStatement(SubscriptionQueries.CREATE_SUBSCRIPTION)) {
            preparedStatement.setObject(1, subscription.getUserId());
            preparedStatement.setObject(2, subscription.getSubscriptionPlanId());
            preparedStatement.setTimestamp(3, Timestamp.valueOf(subscription.getExpiresDate()));
            return Optional.of(getSubscription(preparedStatement));
        } catch (SQLException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Subscription> getById(UUID subscriptionId) {
        try (Connection connection = ConnectionManager.open(); PreparedStatement preparedStatement = connection.prepareStatement(SubscriptionQueries.SELECT_SUBSCRIPTION_BY_ID)) {
            preparedStatement.setObject(1, subscriptionId);
            return Optional.of(getSubscription(preparedStatement));
        } catch (SQLException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Subscription> getAll() {
        try (Connection connection = ConnectionManager.open(); PreparedStatement preparedStatement = connection.prepareStatement(SubscriptionQueries.SELECT_ALL_SUBSCRIPTIONS)) {
            List<Subscription> subscriptions = new ArrayList<>();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                UUID id = resultSet.getObject("id", UUID.class);
                UUID userId = resultSet.getObject("user_id", UUID.class);
                UUID subscriptionPlanId = resultSet.getObject("subscription_plan_id", UUID.class);
                LocalDateTime startedDate = resultSet.getTimestamp("started_date").toLocalDateTime();
                LocalDateTime expiresDate = resultSet.getTimestamp("expires_date").toLocalDateTime();

                Subscription subscription = new Subscription(userId, subscriptionPlanId);
                subscription.setId(id);
                subscription.setStartedDate(startedDate);
                subscription.setExpiresDate(expiresDate);

                subscriptions.add(subscription);
            }
            return subscriptions;
        } catch (SQLException e) {
            throw new NoSubscriptionsFoundException("No subscriptions found");
        }
    }

    @Override
    public Optional<Subscription> update(Subscription subscription, UUID subscriptionId) {
        try (Connection connection = ConnectionManager.open(); PreparedStatement preparedStatement = connection.prepareStatement(SubscriptionQueries.UPDATE_SUBSCRIPTION_BY_ID)) {
            preparedStatement.setObject(1, subscription.getSubscriptionPlanId());
            preparedStatement.setTimestamp(2, Timestamp.valueOf(subscription.getExpiresDate()));
            preparedStatement.setObject(3, subscriptionId);
            return Optional.of(getSubscription(preparedStatement));
        } catch (SQLException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Subscription> delete(UUID subscriptionId) {
        try (Connection connection = ConnectionManager.open(); PreparedStatement preparedStatement = connection.prepareStatement(SubscriptionQueries.DELETE_SUBSCRIPTION_BY_ID)) {
            preparedStatement.setObject(1, subscriptionId);
            return Optional.of(getSubscription(preparedStatement));
        } catch (SQLException e) {
            return Optional.empty();
        }
    }

    private Subscription getSubscription(PreparedStatement preparedStatement) throws SQLException {
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            UUID id = resultSet.getObject("id", UUID.class);
            UUID userId = resultSet.getObject("user_id", UUID.class);
            UUID subscriptionPlanId = resultSet.getObject("subscription_plan_id", UUID.class);
            LocalDateTime startedDate = resultSet.getTimestamp("started_date").toLocalDateTime();
            LocalDateTime expiresDate = resultSet.getTimestamp("expires_date").toLocalDateTime();

            Subscription subscription = new Subscription(userId, subscriptionPlanId);
            subscription.setId(id);
            subscription.setStartedDate(startedDate);
            subscription.setExpiresDate(expiresDate);

            return subscription;
        } else {
            throw new SubscriptionNotFoundException("Subscription not found");
        }
    }
}
