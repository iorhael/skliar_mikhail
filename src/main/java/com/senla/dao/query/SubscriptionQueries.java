package com.senla.dao.query;

public final class SubscriptionQueries {
    public static final String CREATE_SUBSCRIPTION = "INSERT INTO subscriptions (user_id, subscription_plan_id, expires_date) VALUES (?, ?, ?) RETURNING *";
    public static final String SELECT_SUBSCRIPTION_BY_ID = "SELECT * FROM subscriptions WHERE id = ?";
    public static final String SELECT_ALL_SUBSCRIPTIONS = "SELECT * FROM subscriptions";
    public static final String UPDATE_SUBSCRIPTION_BY_ID = "UPDATE subscriptions SET subscription_plan_id = ?, expires_date = ? WHERE id = ? RETURNING *";
    public static final String DELETE_SUBSCRIPTION_BY_ID = "DELETE FROM subscriptions WHERE id = ? RETURNING *";

    private SubscriptionQueries() {}
}
