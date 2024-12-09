package com.senla.api.dao.query;

public final class CategoryQueries {
    public static final String CREATE_CATEGORY = "INSERT INTO categories (name, description, parent_id) VALUES (?, ?, ?) RETURNING *";
    public static final String SELECT_CATEGORY_BY_ID = "SELECT * FROM categories WHERE id = ?";
    public static final String SELECT_ALL_CATEGORIES = "SELECT * FROM categories";
    public static final String UPDATE_CATEGORY_BY_ID = "UPDATE categories SET name = ?, description = ?, parent_id = ? WHERE id = ? RETURNING *";
    public static final String DELETE_CATEGORY_BY_ID = "DELETE FROM categories WHERE id = ? RETURNING *";

    private CategoryQueries() {
    }
}
