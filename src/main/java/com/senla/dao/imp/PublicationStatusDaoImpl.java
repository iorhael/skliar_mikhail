package com.senla.dao.imp;

import com.senla.dao.PublicationStatusDao;
import com.senla.dao.exception.NoPublicationStatusesFoundException;
import com.senla.dao.exception.PublicationStatusNotFoundException;
import com.senla.dao.query.PublicationStatusQueries;
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

public class PublicationStatusDaoImpl implements PublicationStatusDao {
    @Override
    public Optional<PublicationStatus> create(PublicationStatus publicationStatus) {
        try(Connection connection = ConnectionManager.open(); PreparedStatement preparedStatement = connection.prepareStatement(PublicationStatusQueries.CREATE_PUBLICATION_STATUS)) {
            preparedStatement.setObject(1, publicationStatus.getPostId());
            preparedStatement.setObject(2, publicationStatus.getStatusName(), Types.OTHER);
            preparedStatement.setTimestamp(3, Timestamp.valueOf(publicationStatus.getScheduledDate()));
            return Optional.ofNullable(getPublicationStatus(preparedStatement));
        } catch (SQLException e){
            return Optional.empty();
        }
    }

    @Override
    public Optional<PublicationStatus> getById(UUID publicationStatusId) {
        try(Connection connection = ConnectionManager.open(); PreparedStatement preparedStatement = connection.prepareStatement(PublicationStatusQueries.SELECT_PUBLICATION_STATUS_BY_ID)){
            preparedStatement.setObject(1, publicationStatusId);
            return Optional.ofNullable(getPublicationStatus(preparedStatement));
        } catch (SQLException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<PublicationStatus> getAll() {
        try(Connection connection = ConnectionManager.open(); PreparedStatement preparedStatement = connection.prepareStatement(PublicationStatusQueries.SELECT_ALL_PUBLICATION_STATUSES)){
            List<PublicationStatus> publicationStatuses = new ArrayList<>();
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                UUID id = resultSet.getObject("id", UUID.class);
                UUID postId = resultSet.getObject("post_id", UUID.class);
                PostStatus statusName = PostStatus.valueOf(resultSet.getString("status_name"));
                LocalDateTime scheduledDate = resultSet.getTimestamp("scheduled_date").toLocalDateTime();

                publicationStatuses.add(PublicationStatus.builder()
                        .id(id)
                        .postId(postId)
                        .statusName(statusName)
                        .scheduledDate(scheduledDate)
                        .build()

                );
            }
            return publicationStatuses;
        } catch (SQLException e) {
            throw new NoPublicationStatusesFoundException("No publication statuses found");
        }
    }

    @Override
    public Optional<PublicationStatus> update(PublicationStatus publicationStatus, UUID publicationStatusId) {
        try (Connection connection = ConnectionManager.open(); PreparedStatement preparedStatement = connection.prepareStatement(PublicationStatusQueries.UPDATE_PUBLICATION_STATUS_BY_ID)) {
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
        try (Connection connection = ConnectionManager.open(); PreparedStatement preparedStatement = connection.prepareStatement(PublicationStatusQueries.DELETE_PUBLICATION_STATUS_BY_ID)) {
            preparedStatement.setObject(1, publicationStatusId);
            return Optional.ofNullable(getPublicationStatus(preparedStatement));
        } catch (SQLException e) {
            return Optional.empty();
        }
    }

    private PublicationStatus getPublicationStatus(PreparedStatement preparedStatement) throws SQLException {
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            UUID id = resultSet.getObject("id", UUID.class);
            UUID postId = resultSet.getObject("post_id", UUID.class);
            PostStatus statusName = PostStatus.valueOf(resultSet.getString("status_name"));
            LocalDateTime scheduledDate = resultSet.getTimestamp("scheduled_date").toLocalDateTime();

            return PublicationStatus.builder()
                    .id(id)
                    .postId(postId)
                    .statusName(statusName)
                    .scheduledDate(scheduledDate)
                    .build();
        } else {
            throw new PublicationStatusNotFoundException("Publication status not found");
        }
    }
}
