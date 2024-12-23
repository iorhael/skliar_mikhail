package com.senla.api.dao.imp;

import com.senla.api.dao.PollDao;
import com.senla.api.dao.exception.NoPollsFoundException;
import com.senla.api.dao.exception.PollNotFoundException;
import com.senla.api.dao.query.PollQueries;
import com.senla.api.model.Poll;
import com.senla.api.util.ConnectionManager;
import com.senla.di.annotation.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class PollDaoImpl implements PollDao {
    @Override
    public Optional<Poll> create(Poll poll) {
        try (Connection connection = ConnectionManager.open(); PreparedStatement preparedStatement = connection.prepareStatement(PollQueries.CREATE_POLL)) {
            preparedStatement.setObject(1, poll.getPostId());
            preparedStatement.setObject(2, poll.getAuthorId());
            preparedStatement.setString(3, poll.getDescription());
            return Optional.of(getPoll(preparedStatement));
        } catch (SQLException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Poll> getById(UUID pollId) {
        try (Connection connection = ConnectionManager.open(); PreparedStatement preparedStatement = connection.prepareStatement(PollQueries.SELECT_POLL_BY_ID)) {
            preparedStatement.setObject(1, pollId);
            return Optional.of(getPoll(preparedStatement));
        } catch (SQLException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Poll> getAll() {
        try (Connection connection = ConnectionManager.open(); PreparedStatement preparedStatement = connection.prepareStatement(PollQueries.SELECT_ALL_POLLS)) {
            List<Poll> polls = new ArrayList<>();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                UUID id = resultSet.getObject("id", UUID.class);
                UUID postId = resultSet.getObject("post_id", UUID.class);
                UUID authorId = resultSet.getObject("author_id", UUID.class);
                String description = resultSet.getString("description");

                Poll poll = new Poll(authorId, description);
                poll.setId(id);
                poll.setPostId(postId);

                polls.add(poll);
            }
            return polls;
        } catch (SQLException e) {
            throw new NoPollsFoundException("No polls found");
        }
    }

    @Override
    public Optional<Poll> update(Poll poll, UUID pollId) {
        try (Connection connection = ConnectionManager.open(); PreparedStatement preparedStatement = connection.prepareStatement(PollQueries.UPDATE_POLL_BY_ID)) {
            preparedStatement.setString(1, poll.getDescription());
            preparedStatement.setObject(2, pollId);
            return Optional.of(getPoll(preparedStatement));
        } catch (SQLException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Poll> delete(UUID pollId) {
        try (Connection connection = ConnectionManager.open(); PreparedStatement preparedStatement = connection.prepareStatement(PollQueries.DELETE_POLL_BY_ID)) {
            preparedStatement.setObject(1, pollId);
            return Optional.of(getPoll(preparedStatement));
        } catch (SQLException e) {
            return Optional.empty();
        }
    }

    private Poll getPoll(PreparedStatement preparedStatement) throws SQLException {
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            UUID id = resultSet.getObject("id", UUID.class);
            UUID postId = resultSet.getObject("post_id", UUID.class);
            UUID authorId = resultSet.getObject("author_id", UUID.class);
            String description = resultSet.getString("description");

            Poll poll = new Poll(authorId, description);
            poll.setId(id);
            poll.setPostId(postId);

            return poll;
        } else {
            throw new PollNotFoundException("Poll not found");
        }
    }
}
