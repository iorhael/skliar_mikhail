package com.senla.api.dao.query;

public final class CommentQueries {
    public static final String CREATE_COMMENT = "INSERT INTO comments (post_id, author_id, content, parent_id) VALUES (?, ?, ?, ?) RETURNING *";
    public static final String SELECT_COMMENT_BY_ID = "SELECT * FROM comments WHERE id = ?";
    public static final String SELECT_ALL_COMMENTS = "SELECT * FROM comments";
    public static final String UPDATE_COMMENT_BY_ID = "UPDATE comments SET content = ?, updated_date = ? WHERE id = ? RETURNING *";
    public static final String DELETE_COMMENT_BY_ID = "DELETE FROM comments WHERE id = ? RETURNING *";

    private CommentQueries() {
    }
}
