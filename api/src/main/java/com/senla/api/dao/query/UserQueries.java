package com.senla.api.dao.query;

public final class UserQueries {
    public static final String CREATE_USER = "INSERT INTO users (username, email, password) VALUES (?, ?, ?) RETURNING *";
    public static final String SELECT_USER_BY_ID = "SELECT * FROM users WHERE id = ?";
    public static final String SELECT_ALL_USERS = "SELECT * FROM users";
    public static final String UPDATE_USER_BY_ID = "UPDATE users SET username = ?, email = ?, password = ? WHERE id = ? RETURNING *";
    public static final String DELETE_USER_BY_ID = "DELETE FROM users WHERE id = ? RETURNING *";

    private UserQueries() {
    }
}
