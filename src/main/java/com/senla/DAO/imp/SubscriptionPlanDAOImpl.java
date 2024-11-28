package com.senla.DAO.imp;

import com.senla.DAO.SubscriptionPlanDAO;
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

public class SubscriptionPlanDAOImpl implements SubscriptionPlanDAO {
    private static final String CREATE_SUBSCRIPTION_PLAN = "INSERT INTO subscription_plans (name, price_per_month) VALUES (?, ?)";
    private static final String SELECT_SUBSCRIPTION_PLAN_BY_ID = "SELECT * FROM subscription_plans WHERE id = ?";
    private static final String SELECT_ALL_SUBSCRIPTION_PLANS = "SELECT * FROM subscription_plans";
    private static final String UPDATE_SUBSCRIPTION_PLAN_BY_ID = "UPDATE subscription_plans SET name = ?, price_per_month = ? WHERE id = ? RETURNING *";
    private static final String DELETE_SUBSCRIPTION_PLAN_BY_ID = "DELETE FROM subscription_plans WHERE id = ? RETURNING *";

    @Override
    public boolean create(SubscriptionPlan subscriptionPlan) {
        try(Connection connection = ConnectionManager.open(); PreparedStatement preparedStatement = connection.prepareStatement(CREATE_SUBSCRIPTION_PLAN)) {
            preparedStatement.setObject(1, subscriptionPlan.getName(), Types.OTHER);
            preparedStatement.setBigDecimal(2, subscriptionPlan.getPricePerMonth());
            preparedStatement.executeUpdate();
        } catch (SQLException e){
            return false;
        }
        return true;
    }

    @Override
    public Optional<SubscriptionPlan> getById(UUID subscriptionPlanId) {
        try(Connection connection = ConnectionManager.open(); PreparedStatement preparedStatement = connection.prepareStatement(SELECT_SUBSCRIPTION_PLAN_BY_ID)){
            preparedStatement.setObject(1, subscriptionPlanId);
            return Optional.ofNullable(getSubscriptionPlan(preparedStatement));
        } catch (SQLException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<SubscriptionPlan> getAll() {
        try(Connection connection = ConnectionManager.open(); PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_SUBSCRIPTION_PLANS)){
            List<SubscriptionPlan> subscriptionPlans = new ArrayList<>();
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                UUID id = resultSet.getObject("id", UUID.class);
                SubscriptionType name = SubscriptionType.valueOf(resultSet.getString("name"));
                BigDecimal pricePerMonth = resultSet.getBigDecimal("price_per_month");

                subscriptionPlans.add(new SubscriptionPlan.Builder()
                        .id(id)
                        .name(name)
                        .pricePerMonth(pricePerMonth)
                        .build()
                );
            }
            return subscriptionPlans;
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public Optional<SubscriptionPlan> update(SubscriptionPlan subscriptionPlan, UUID subscriptionPlanId) {
        try (Connection connection = ConnectionManager.open(); PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SUBSCRIPTION_PLAN_BY_ID)) {
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
        try (Connection connection = ConnectionManager.open(); PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SUBSCRIPTION_PLAN_BY_ID)) {
            preparedStatement.setObject(1, subscriptionPlanId);
            return Optional.ofNullable(getSubscriptionPlan(preparedStatement));
        } catch (SQLException e) {
            return Optional.empty();
        }
    }

    private SubscriptionPlan getSubscriptionPlan(PreparedStatement preparedStatement) throws SQLException {
        SubscriptionPlan subscriptionPlan = null;
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            UUID id = resultSet.getObject("id", UUID.class);
            SubscriptionType name = SubscriptionType.valueOf(resultSet.getString("name"));
            BigDecimal pricePerMonth = resultSet.getBigDecimal("price_per_month");

            subscriptionPlan = new SubscriptionPlan.Builder()
                    .id(id)
                    .name(name)
                    .pricePerMonth(pricePerMonth)
                    .build();
        }
        return subscriptionPlan;
    }
}
