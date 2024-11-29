package com.senla.dao.imp;

import com.senla.dao.SubscriptionPlanDao;
import com.senla.dao.exception.NoSubscriptionPlansFoundException;
import com.senla.dao.exception.SubscriptionNotFoundException;
import com.senla.dao.query.SubscriptionPlanQueries;
import com.senla.model.SubscriptionPlan;
import com.senla.model.SubscriptionType;
import com.senla.util.ConnectionManager;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class SubscriptionPlanDaoImpl implements SubscriptionPlanDao {
    @Override
    public Optional<SubscriptionPlan> create(SubscriptionPlan subscriptionPlan) {
        try (Connection connection = ConnectionManager.open(); PreparedStatement preparedStatement = connection.prepareStatement(SubscriptionPlanQueries.CREATE_SUBSCRIPTION_PLAN)) {
            preparedStatement.setObject(1, subscriptionPlan.getName(), Types.OTHER);
            preparedStatement.setBigDecimal(2, subscriptionPlan.getPricePerMonth());
            return Optional.ofNullable(getSubscriptionPlan(preparedStatement));
        } catch (SQLException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<SubscriptionPlan> getById(UUID subscriptionPlanId) {
        try (Connection connection = ConnectionManager.open(); PreparedStatement preparedStatement = connection.prepareStatement(SubscriptionPlanQueries.SELECT_SUBSCRIPTION_PLAN_BY_ID)) {
            preparedStatement.setObject(1, subscriptionPlanId);
            return Optional.ofNullable(getSubscriptionPlan(preparedStatement));
        } catch (SQLException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<SubscriptionPlan> getAll() {
        try (Connection connection = ConnectionManager.open(); PreparedStatement preparedStatement = connection.prepareStatement(SubscriptionPlanQueries.SELECT_ALL_SUBSCRIPTION_PLANS)) {
            List<SubscriptionPlan> subscriptionPlans = new ArrayList<>();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                UUID id = resultSet.getObject("id", UUID.class);
                SubscriptionType name = SubscriptionType.valueOf(resultSet.getString("name"));
                BigDecimal pricePerMonth = resultSet.getBigDecimal("price_per_month");

                subscriptionPlans.add(SubscriptionPlan.builder()
                        .id(id)
                        .name(name)
                        .pricePerMonth(pricePerMonth)
                        .build()
                );
            }
            return subscriptionPlans;
        } catch (SQLException e) {
            throw new NoSubscriptionPlansFoundException("No subscription plans found");
        }
    }

    @Override
    public Optional<SubscriptionPlan> update(SubscriptionPlan subscriptionPlan, UUID subscriptionPlanId) {
        try (Connection connection = ConnectionManager.open(); PreparedStatement preparedStatement = connection.prepareStatement(SubscriptionPlanQueries.UPDATE_SUBSCRIPTION_PLAN_BY_ID)) {
            preparedStatement.setObject(1, subscriptionPlan.getName(), Types.OTHER);
            preparedStatement.setBigDecimal(2, subscriptionPlan.getPricePerMonth());
            preparedStatement.setObject(3, subscriptionPlanId);
            return Optional.ofNullable(getSubscriptionPlan(preparedStatement));
        } catch (SQLException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<SubscriptionPlan> delete(UUID subscriptionPlanId) {
        try (Connection connection = ConnectionManager.open(); PreparedStatement preparedStatement = connection.prepareStatement(SubscriptionPlanQueries.DELETE_SUBSCRIPTION_PLAN_BY_ID)) {
            preparedStatement.setObject(1, subscriptionPlanId);
            return Optional.ofNullable(getSubscriptionPlan(preparedStatement));
        } catch (SQLException e) {
            return Optional.empty();
        }
    }

    private SubscriptionPlan getSubscriptionPlan(PreparedStatement preparedStatement) throws SQLException {
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            UUID id = resultSet.getObject("id", UUID.class);
            SubscriptionType name = SubscriptionType.valueOf(resultSet.getString("name"));
            BigDecimal pricePerMonth = resultSet.getBigDecimal("price_per_month");

            return SubscriptionPlan.builder()
                    .id(id)
                    .name(name)
                    .pricePerMonth(pricePerMonth)
                    .build();
        } else {
            throw new SubscriptionNotFoundException("Subscription plan not found");
        }
    }
}
