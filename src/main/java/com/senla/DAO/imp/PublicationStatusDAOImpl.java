package com.senla.DAO.imp;

import com.senla.DAO.PublicationStatusDAO;
import com.senla.model.PostStatus;
import com.senla.model.PublicationStatus;
import com.senla.util.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class PublicationStatusDAOImpl implements PublicationStatusDAO {
    private static final String CREATE_PUBLICATION_STATUS = "INSERT INTO publication_statuses (post_id, status_name, scheduled_date) VALUES (?, ?, ?)";
    private static final String SELECT_PUBLICATION_STATUS_BY_ID = "SELECT * FROM publication_statuses WHERE id = ?";
    private static final String SELECT_ALL_PUBLICATION_STATUSES = "SELECT * FROM publication_statuses";
    private static final String UPDATE_PUBLICATION_STATUS_BY_ID = "UPDATE publication_statuses SET status_name = ?, scheduled_date = ? WHERE id = ? RETURNING *";
    private static final String DELETE_PUBLICATION_STATUS_BY_ID = "DELETE FROM publication_statuses WHERE id = ? RETURNING *";

    @Override
    public boolean create(PublicationStatus publicationStatus) {
        try(Connection connection = ConnectionManager.open(); PreparedStatement preparedStatement = connection.prepareStatement(CREATE_PUBLICATION_STATUS)) {
            preparedStatement.setObject(1, publicationStatus.getPostId());
            preparedStatement.setObject(2, publicationStatus.getStatusName(), Types.OTHER);
            preparedStatement.setTimestamp(3, Timestamp.valueOf(publicationStatus.getScheduledDate()));
            preparedStatement.executeUpdate();
        } catch (SQLException e){
            return false;
        }
        return true;
    }

    @Override
    public Optional<PublicationStatus> getById(UUID publicationStatusId) {
        try(Connection connection = ConnectionManager.open(); PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PUBLICATION_STATUS_BY_ID)){
            preparedStatement.setObject(1, publicationStatusId);
            return Optional.ofNullable(getPublicationStatus(preparedStatement));
        } catch (SQLException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<PublicationStatus> getAll() {
        try(Connection connection = ConnectionManager.open(); PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PUBLICATION_STATUSES)){
            List<PublicationStatus> publicationStatuses = new ArrayList<>();
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                UUID id = resultSet.getObject("id", UUID.class);
                UUID postId = resultSet.getObject("post_id", UUID.class);
                PostStatus statusName = PostStatus.valueOf(resultSet.getString("status_name"));
                LocalDateTime scheduledDate = resultSet.getTimestamp("scheduled_date").toLocalDateTime();

                publicationStatuses.add(new PublicationStatus.Builder()
                        .id(id)
                        .postId(postId)
                        .statusName(statusName)
                        .scheduledDate(scheduledDate)
                        .build()

                );
            }
            return publicationStatuses;
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public Optional<PublicationStatus> update(PublicationStatus publicationStatus, UUID publicationStatusId) {
        try (Connection connection = ConnectionManager.open(); PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PUBLICATION_STATUS_BY_ID)) {
            preparedStatement.setObject(1, publicationStatus.getStatusName(), Types.OTHER);
            preparedStatement.setTimestamp(2, Timestamp.valueOf(publicationStatus.getScheduledDate()));
            preparedStatement.setObject(3, publicationStatusId);
            return Optional.ofNullable(getPublicationStatus(preparedStatement));
        } catch (SQLException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<PublicationStatus> delete(UUID publicationStatusId) {
        try (Connection connection = ConnectionManager.open(); PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PUBLICATION_STATUS_BY_ID)) {
            preparedStatement.setObject(1, publicationStatusId);
            return Optional.ofNullable(getPublicationStatus(preparedStatement));
        } catch (SQLException e) {
            return Optional.empty();
        }
    }

    private PublicationStatus getPublicationStatus(PreparedStatement preparedStatement) throws SQLException {
        PublicationStatus publicationStatus = null;
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            UUID id = resultSet.getObject("id", UUID.class);
            UUID postId = resultSet.getObject("post_id", UUID.class);
            PostStatus statusName = PostStatus.valueOf(resultSet.getString("status_name"));
            LocalDateTime scheduledDate = resultSet.getTimestamp("scheduled_date").toLocalDateTime();

            publicationStatus = new PublicationStatus.Builder()
                    .id(id)
                    .postId(postId)
                    .statusName(statusName)
                    .scheduledDate(scheduledDate)
                    .build();
        }
        return publicationStatus;
    }
}
