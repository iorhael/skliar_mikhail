package com.senla.repository.query;

public final class PollOptionQueries {
    public static final String CREATE_POLL_OPTION = "INSERT INTO poll_options (poll_id, description) VALUES (?, ?) RETURNING *";
    public static final String SELECT_POLL_OPTION_BY_ID = "SELECT * FROM poll_options WHERE id = ?";
    public static final String SELECT_ALL_POLL_OPTIONS = "SELECT * FROM poll_options";
    public static final String UPDATE_POLL_OPTION_BY_ID = "UPDATE poll_options SET description = ? WHERE id = ? RETURNING *";
    public static final String DELETE_POLL_OPTION_BY_ID = "DELETE FROM poll_options WHERE id = ? RETURNING *";

    private PollOptionQueries() {
    }
}
