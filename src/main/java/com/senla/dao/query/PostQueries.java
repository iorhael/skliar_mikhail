package com.senla.dao.query;

public final class PostQueries {
    public static final String CREATE_POST = "INSERT INTO posts (author_id, title, content, publication_date, subscription_plan_id) VALUES (?, ?, ?, ?, ?) RETURNING *";
    public static final String SELECT_POST_BY_ID = "SELECT * FROM posts WHERE id = ?";
    public static final String SELECT_ALL_POSTS = "SELECT * FROM posts";
    public static final String UPDATE_POST_BY_ID = "UPDATE posts SET title = ?, content = ?, updated_date = ?, publication_date = ?, subscription_plan_id = ?, views_total = ? WHERE id = ? RETURNING *";
    public static final String DELETE_POST_BY_ID = "DELETE FROM posts WHERE id = ? RETURNING *";

    private PostQueries() {
    }
}
