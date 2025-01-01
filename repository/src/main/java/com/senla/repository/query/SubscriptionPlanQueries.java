package com.senla.repository.query;

public final class SubscriptionPlanQueries {
    public static final String CREATE_SUBSCRIPTION_PLAN = "INSERT INTO subscription_plans (name, price_per_month) VALUES (?, ?) RETURNING *";
    public static final String SELECT_SUBSCRIPTION_PLAN_BY_ID = "SELECT * FROM subscription_plans WHERE id = ?";
    public static final String SELECT_ALL_SUBSCRIPTION_PLANS = "SELECT * FROM subscription_plans";
    public static final String UPDATE_SUBSCRIPTION_PLAN_BY_ID = "UPDATE subscription_plans SET name = ?, price_per_month = ? WHERE id = ? RETURNING *";
    public static final String DELETE_SUBSCRIPTION_PLAN_BY_ID = "DELETE FROM subscription_plans WHERE id = ? RETURNING *";

    private SubscriptionPlanQueries() {
    }
}
