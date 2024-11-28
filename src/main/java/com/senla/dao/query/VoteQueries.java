package com.senla.dao.query;

public final class VoteQueries {
    public static final String CREATE_VOTE = "INSERT INTO votes (poll_option_id, user_id, vote_date) VALUES (?, ?, ?) RETURNING *";
    public static final String SELECT_VOTE_BY_ID = "SELECT * FROM votes WHERE poll_option_id = ? AND user_id = ?";
    public static final String SELECT_ALL_VOTES = "SELECT * FROM votes";
    public static final String UPDATE_VOTE_BY_ID = "UPDATE votes SET vote_date = ? WHERE poll_option_id = ? AND user_id = ? RETURNING *";
    public static final String DELETE_VOTE_BY_ID = "DELETE FROM votes WHERE poll_option_id = ? AND user_id = ? RETURNING *";

    private VoteQueries() {}
}
