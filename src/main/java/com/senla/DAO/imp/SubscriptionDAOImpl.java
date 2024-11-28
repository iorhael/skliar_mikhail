package com.senla.DAO.imp;

import com.senla.DAO.SubscriptionDAO;
import com.senla.model.Subscription;
import com.senla.util.ConnectionManager;

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

public class SubscriptionDAOImpl implements SubscriptionDAO {
    private static final String CREATE_SUBSCRIPTION = "INSERT INTO subscriptions (user_id, subscription_plan_id, expires_date) VALUES (?, ?, ?)";
    private static final String SELECT_SUBSCRIPTION_BY_ID = "SELECT * FROM subscriptions WHERE id = ?";
    private static final String SELECT_ALL_SUBSCRIPTIONS = "SELECT * FROM subscriptions";
    private static final String UPDATE_SUBSCRIPTION_BY_ID = "UPDATE subscriptions SET subscription_plan_id = ?, expires_date = ? WHERE id = ? RETURNING *";
    private static final String DELETE_SUBSCRIPTION_BY_ID = "DELETE FROM subscriptions WHERE id = ? RETURNING *";

    @Override
    public boolean create(Subscription subscription) {
        try(Connection connection = ConnectionManager.open(); PreparedStatement preparedStatement = connection.prepareStatement(CREATE_SUBSCRIPTION)) {
            preparedStatement.setObject(1, subscription.getUserId());
            preparedStatement.setObject(2, subscription.getSubscriptionPlanId());
            preparedStatement.setTimestamp(3, Timestamp.valueOf(subscription.getExpiresDate()));
            preparedStatement.executeUpdate();
        } catch (SQLException e){
            return false;
        }
        return true;
    }

    @Override
    public Optional<Subscription> getById(UUID subscriptionId) {
        try(Connection connection = ConnectionManager.open(); PreparedStatement preparedStatement = connection.prepareStatement(SELECT_SUBSCRIPTION_BY_ID)){
            preparedStatement.setObject(1, subscriptionId);
            return Optional.ofNullable(getSubscription(preparedStatement));
        } catch (SQLException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Subscription> getAll() {
        try(Connection connection = ConnectionManager.open(); PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_SUBSCRIPTIONS)){
            List<Subscription> subscriptions = new ArrayList<>();
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                UUID id = resultSet.getObject("id", UUID.class);
                UUID userId = resultSet.getObject("user_id", UUID.class);
                UUID subscriptionPlanId = resultSet.getObject("subscription_plan_id", UUID.class);
                LocalDateTime startedDate = resultSet.getTimestamp("started_date").toLocalDateTime();
                LocalDateTime expiresDate = resultSet.getTimestamp("expires_date").toLocalDateTime();

                subscriptions.add(new Subscription.Builder()
                        .id(id)
                        .userId(userId)
                        .subscriptionPlanId(subscriptionPlanId)
                        .startedDate(startedDate)
                        .expiresDate(expiresDate)
                        .build()
                );
            }
            return subscriptions;
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public Optional<Subscription> update(Subscription subscription, UUID subscriptionId) {
        try (Connection connection = ConnectionManager.open(); PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SUBSCRIPTION_BY_ID)) {
            preparedStatement.setObject(1, subscription.getSubscriptionPlanId());
            preparedStatement.setTimestamp(2, Timestamp.valueOf(subscription.getExpiresDate()));
            preparedStatement.setObject(3, subscriptionId);
            return Optional.ofNullable(getSubscription(preparedStatement));
        } catch (SQLException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Subscription> delete(UUID subscriptionId) {
        try (Connection connection = ConnectionManager.open(); PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SUBSCRIPTION_BY_ID)) {
            preparedStatement.setObject(1, subscriptionId);
            return Optional.ofNullable(getSubscription(preparedStatement));
        } catch (SQLException e) {
            return Optional.empty();
        }
    }

    private Subscription getSubscription(PreparedStatement preparedStatement) throws SQLException {
        Subscription subscription = null;
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            UUID id = resultSet.getObject("id", UUID.class);
            UUID userId = resultSet.getObject("user_id", UUID.class);
            UUID subscriptionPlanId = resultSet.getObject("subscription_plan_id", UUID.class);
            LocalDateTime startedDate = resultSet.getTimestamp("started_date").toLocalDateTime();
            LocalDateTime expiresDate = resultSet.getTimestamp("expires_date").toLocalDateTime();

            subscription = new Subscription.Builder()
                    .id(id)
                    .userId(userId)
                    .subscriptionPlanId(subscriptionPlanId)
                    .startedDate(startedDate)
                    .expiresDate(expiresDate)
                    .build();
        }
        return subscription;
    }
}
