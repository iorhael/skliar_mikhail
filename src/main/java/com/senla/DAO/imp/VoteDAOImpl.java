package com.senla.DAO.imp;

import com.senla.DAO.VoteDAO;
import com.senla.model.Tag;
import com.senla.model.Vote;
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

public class VoteDAOImpl implements VoteDAO {
    private static final String CREATE_VOTE = "INSERT INTO votes (poll_option_id, user_id, vote_date) VALUES (?, ?, ?)";
    private static final String SELECT_VOTE_BY_ID = "SELECT * FROM votes WHERE poll_option_id = ? AND user_id = ?";
    private static final String SELECT_ALL_VOTES = "SELECT * FROM votes";
    private static final String UPDATE_VOTE_BY_ID = "UPDATE votes SET vote_date = ? WHERE poll_option_id = ? AND user_id = ? RETURNING *";
    private static final String DELETE_VOTE_BY_ID = "DELETE FROM votes WHERE poll_option_id = ? AND user_id = ? RETURNING *";

    @Override
    public boolean create(Vote vote) {
        try(Connection connection = ConnectionManager.open(); PreparedStatement preparedStatement = connection.prepareStatement(CREATE_VOTE)) {
            preparedStatement.setObject(1, vote.getPollOptionId());
            preparedStatement.setObject(2, vote.getUserId());
            preparedStatement.setTimestamp(3, Timestamp.valueOf(vote.getVoteDate()));
            preparedStatement.executeUpdate();
        } catch (SQLException e){
            return false;
        }
        return true;
    }

    @Override
    public Optional<Vote> getById(UUID pollOptionId, UUID userId) {
        try(Connection connection = ConnectionManager.open(); PreparedStatement preparedStatement = connection.prepareStatement(SELECT_VOTE_BY_ID)){
            preparedStatement.setObject(1, pollOptionId);
            preparedStatement.setObject(2, userId);
            return Optional.ofNullable(getVote(preparedStatement));
        } catch (SQLException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Vote> getAll() {
        try(Connection connection = ConnectionManager.open(); PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_VOTES)){
            List<Vote> votes = new ArrayList<>();
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                UUID pollOptionId = resultSet.getObject("poll_option_id", UUID.class);
                UUID userId = resultSet.getObject("user_id", UUID.class);
                LocalDateTime voteDate = resultSet.getTimestamp("vote_date").toLocalDateTime();

                votes.add(new Vote.Builder()
                        .pollOptionId(pollOptionId)
                        .userId(userId)
                        .voteDate(voteDate)
                        .build()
                );
            }
            return votes;
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public Optional<Vote> update(Vote vote, UUID pollOptionId, UUID userId) {
        try (Connection connection = ConnectionManager.open(); PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_VOTE_BY_ID)) {
            preparedStatement.setTimestamp(1, Timestamp.valueOf(vote.getVoteDate()));
            preparedStatement.setObject(2, pollOptionId);
            preparedStatement.setObject(3, userId);
            return Optional.ofNullable(getVote(preparedStatement));
        } catch (SQLException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Vote> delete(UUID pollOptionId, UUID userId) {
        try (Connection connection = ConnectionManager.open(); PreparedStatement preparedStatement = connection.prepareStatement(DELETE_VOTE_BY_ID)) {
            preparedStatement.setObject(1, pollOptionId);
            preparedStatement.setObject(2, userId);
            return Optional.ofNullable(getVote(preparedStatement));
        } catch (SQLException e) {
            return Optional.empty();
        }
    }

    private Vote getVote(PreparedStatement preparedStatement) throws SQLException {
        Vote vote = null;
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            UUID pollOptionId = resultSet.getObject("poll_option_id", UUID.class);
            UUID userId = resultSet.getObject("user_id", UUID.class);
            LocalDateTime voteDate = resultSet.getTimestamp("vote_date").toLocalDateTime();

            vote = new Vote.Builder()
                    .pollOptionId(pollOptionId)
                    .userId(userId)
                    .voteDate(voteDate)
                    .build();
        }
        return vote;
    }
}
