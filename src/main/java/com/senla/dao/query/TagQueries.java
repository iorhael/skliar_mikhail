package com.senla.dao.query;

public final class TagQueries {
    public static final String CREATE_TAG = "INSERT INTO tags (name) VALUES (?) RETURNING *";
    public static final String SELECT_TAG_BY_ID = "SELECT * FROM tags WHERE id = ?";
    public static final String SELECT_ALL_TAGS = "SELECT * FROM tags";
    public static final String UPDATE_TAG_BY_ID = "UPDATE tags SET name = ? WHERE id = ? RETURNING *";
    public static final String DELETE_TAG_BY_ID = "DELETE FROM tags WHERE id = ? RETURNING *";

    private TagQueries() {}
}
