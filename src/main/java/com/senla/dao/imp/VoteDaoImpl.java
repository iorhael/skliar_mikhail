package com.senla.dao.imp;

import com.senla.dao.VoteDao;
import com.senla.dao.exception.NoVotesFoundException;
import com.senla.dao.exception.VoteNotFoundException;
import com.senla.dao.query.VoteQueries;
import com.senla.model.Vote;
import com.senla.model.VoteId;
import com.senla.util.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class VoteDaoImpl implements VoteDao {
    @Override
    public Optional<Vote> create(Vote vote) {
        try (Connection connection = ConnectionManager.open(); PreparedStatement preparedStatement = connection.prepareStatement(VoteQueries.CREATE_VOTE)) {
            preparedStatement.setObject(1, vote.getPollOptionId());
            preparedStatement.setObject(2, vote.getUserId());
            preparedStatement.setTimestamp(3, Timestamp.valueOf(vote.getVoteDate()));
            return Optional.ofNullable(getVote(preparedStatement));
        } catch (SQLException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Vote> getById(VoteId voteId) {
        try (Connection connection = ConnectionManager.open(); PreparedStatement preparedStatement = connection.prepareStatement(VoteQueries.SELECT_VOTE_BY_ID)) {
            preparedStatement.setObject(1, voteId.pollOptionId());
            preparedStatement.setObject(2, voteId.userId());
            return Optional.ofNullable(getVote(preparedStatement));
        } catch (SQLException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Vote> getAll() {
        try (Connection connection = ConnectionManager.open(); PreparedStatement preparedStatement = connection.prepareStatement(VoteQueries.SELECT_ALL_VOTES)) {
            List<Vote> votes = new ArrayList<>();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                UUID pollOptionId = resultSet.getObject("poll_option_id", UUID.class);
                UUID userId = resultSet.getObject("user_id", UUID.class);
                LocalDateTime voteDate = resultSet.getTimestamp("vote_date").toLocalDateTime();

                votes.add(Vote.builder()
                        .pollOptionId(pollOptionId)
                        .userId(userId)
                        .voteDate(voteDate)
                        .build()
                );
            }
            return votes;
        } catch (SQLException e) {
            throw new NoVotesFoundException("No votes found");
        }
    }

    @Override
    public Optional<Vote> update(Vote vote, VoteId voteId) {
        try (Connection connection = ConnectionManager.open(); PreparedStatement preparedStatement = connection.prepareStatement(VoteQueries.UPDATE_VOTE_BY_ID)) {
            preparedStatement.setTimestamp(1, Timestamp.valueOf(vote.getVoteDate()));
            preparedStatement.setObject(2, voteId.pollOptionId());
            preparedStatement.setObject(3, vote.getUserId());
            return Optional.ofNullable(getVote(preparedStatement));
        } catch (SQLException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Vote> delete(VoteId voteId) {
        try (Connection connection = ConnectionManager.open(); PreparedStatement preparedStatement = connection.prepareStatement(VoteQueries.DELETE_VOTE_BY_ID)) {
            preparedStatement.setObject(1, voteId.pollOptionId());
            preparedStatement.setObject(2, voteId.userId());
            return Optional.ofNullable(getVote(preparedStatement));
        } catch (SQLException e) {
            return Optional.empty();
        }
    }

    private Vote getVote(PreparedStatement preparedStatement) throws SQLException {
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            UUID pollOptionId = resultSet.getObject("poll_option_id", UUID.class);
            UUID userId = resultSet.getObject("user_id", UUID.class);
            LocalDateTime voteDate = resultSet.getTimestamp("vote_date").toLocalDateTime();

            return Vote.builder()
                    .pollOptionId(pollOptionId)
                    .userId(userId)
                    .voteDate(voteDate)
                    .build();

        } else {
            throw new VoteNotFoundException("Vote not found");
        }
    }
}
