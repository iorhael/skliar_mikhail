package com.senla.DAO.imp;

import com.senla.DAO.PollDAO;
import com.senla.model.Poll;
import com.senla.util.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class PollDAOImpl implements PollDAO {
    private static final String CREATE_POLL = "INSERT INTO polls (post_id, author_id, description) VALUES (?, ?, ?)";
    private static final String SELECT_POLL_BY_ID = "SELECT * FROM polls WHERE id = ?";
    private static final String SELECT_ALL_POLLS = "SELECT * FROM polls";
    private static final String UPDATE_POLL_BY_ID = "UPDATE polls SET description = ? WHERE id = ? RETURNING *";
    private static final String DELETE_POLL_BY_ID = "DELETE FROM polls WHERE id = ? RETURNING *";

    @Override
    public boolean create(Poll poll) {
        try(Connection connection = ConnectionManager.open(); PreparedStatement preparedStatement = connection.prepareStatement(CREATE_POLL)) {
            preparedStatement.setObject(1, poll.getPostId());
            preparedStatement.setObject(2, poll.getAuthorId());
            preparedStatement.setString(3, poll.getDescription());
            preparedStatement.executeUpdate();
        } catch (SQLException e){
            return false;
        }
        return true;
    }

    @Override
    public Optional<Poll> getById(UUID pollId) {
        try(Connection connection = ConnectionManager.open(); PreparedStatement preparedStatement = connection.prepareStatement(SELECT_POLL_BY_ID)){
            preparedStatement.setObject(1, pollId);
            return Optional.ofNullable(getPoll(preparedStatement));
        } catch (SQLException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Poll> getAll() {
        try(Connection connection = ConnectionManager.open(); PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_POLLS)){
            List<Poll> polls = new ArrayList<>();
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                UUID id = resultSet.getObject("id", UUID.class);
                UUID postId = resultSet.getObject("post_id", UUID.class);
                UUID authorId = resultSet.getObject("author_id", UUID.class);
                String description = resultSet.getString("description");

                polls.add(new Poll.Builder()
                        .id(id)
                        .postId(postId)
                        .authorId(authorId)
                        .description(description)
                        .build()
                );
            }
            return polls;
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public Optional<Poll> update(Poll poll, UUID pollId) {
        try (Connection connection = ConnectionManager.open(); PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_POLL_BY_ID)) {
            preparedStatement.setString(1, poll.getDescription());
            preparedStatement.setObject(2, pollId);
            return Optional.ofNullable(getPoll(preparedStatement));
        } catch (SQLException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Poll> delete(UUID pollId) {
        try (Connection connection = ConnectionManager.open(); PreparedStatement preparedStatement = connection.prepareStatement(DELETE_POLL_BY_ID)) {
            preparedStatement.setObject(1, pollId);
            return Optional.ofNullable(getPoll(preparedStatement));
        } catch (SQLException e) {
            return Optional.empty();
        }
    }

    private Poll getPoll(PreparedStatement preparedStatement) throws SQLException {
        Poll poll = null;
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            UUID id = resultSet.getObject("id", UUID.class);
            UUID postId = resultSet.getObject("post_id", UUID.class);
            UUID authorId = resultSet.getObject("author_id", UUID.class);
            String description = resultSet.getString("description");

            poll = new Poll.Builder()
                    .id(id)
                    .postId(postId)
                    .authorId(authorId)
                    .description(description)
                    .build();
        }
        return poll;
    }
}
