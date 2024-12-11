package com.senla.api.dao.query;

public final class PollQueries {
    public static final String CREATE_POLL = "INSERT INTO polls (post_id, author_id, description) VALUES (?, ?, ?) RETURNING *";
    public static final String SELECT_POLL_BY_ID = "SELECT * FROM polls WHERE id = ?";
    public static final String SELECT_ALL_POLLS = "SELECT * FROM polls";
    public static final String UPDATE_POLL_BY_ID = "UPDATE polls SET description = ? WHERE id = ? RETURNING *";
    public static final String DELETE_POLL_BY_ID = "DELETE FROM polls WHERE id = ? RETURNING *";

    private PollQueries() {
    }
}
