package com.senla.api.dao.query;

public final class RoleQueries {
    public static final String CREATE_ROLE = "INSERT INTO roles (user_id, name) VALUES (?, ?) RETURNING *";
    public static final String SELECT_ROLE_BY_ID = "SELECT * FROM roles WHERE id = ?";
    public static final String SELECT_ALL_ROLES = "SELECT * FROM roles";
    public static final String UPDATE_ROLE_BY_ID = "UPDATE roles SET name = ? WHERE id = ? RETURNING *";
    public static final String DELETE_ROLE_BY_ID = "DELETE FROM roles WHERE id = ? RETURNING *";

    private RoleQueries() {
    }
}
