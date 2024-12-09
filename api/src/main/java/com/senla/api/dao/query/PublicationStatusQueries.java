package com.senla.api.dao.query;

public final class PublicationStatusQueries {
    public static final String CREATE_PUBLICATION_STATUS = "INSERT INTO publication_statuses (post_id, status_name, scheduled_date) VALUES (?, ?, ?) RETURNING *";
    public static final String SELECT_PUBLICATION_STATUS_BY_ID = "SELECT * FROM publication_statuses WHERE id = ?";
    public static final String SELECT_ALL_PUBLICATION_STATUSES = "SELECT * FROM publication_statuses";
    public static final String UPDATE_PUBLICATION_STATUS_BY_ID = "UPDATE publication_statuses SET status_name = ?, scheduled_date = ? WHERE id = ? RETURNING *";
    public static final String DELETE_PUBLICATION_STATUS_BY_ID = "DELETE FROM publication_statuses WHERE id = ? RETURNING *";

    private PublicationStatusQueries() {
    }
}
