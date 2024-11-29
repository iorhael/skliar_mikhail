package com.senla.dao.imp;

import com.senla.dao.PollOptionDao;
import com.senla.dao.exception.NoPollOptionsFoundException;
import com.senla.dao.exception.PollOptionNotFoundException;
import com.senla.dao.query.PollOptionQueries;
import com.senla.model.PollOption;
import com.senla.util.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class PollOptionDaoImpl implements PollOptionDao {
    @Override
    public Optional<PollOption> create(PollOption pollOption) {
        try (Connection connection = ConnectionManager.open(); PreparedStatement preparedStatement = connection.prepareStatement(PollOptionQueries.CREATE_POLL_OPTION)) {
            preparedStatement.setObject(1, pollOption.getPollId());
            preparedStatement.setString(2, pollOption.getDescription());
            return Optional.ofNullable(getPollOption(preparedStatement));
        } catch (SQLException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<PollOption> getById(UUID pollOption) {
        try (Connection connection = ConnectionManager.open(); PreparedStatement preparedStatement = connection.prepareStatement(PollOptionQueries.SELECT_POLL_OPTION_BY_ID)) {
            preparedStatement.setObject(1, pollOption);
            return Optional.ofNullable(getPollOption(preparedStatement));
        } catch (SQLException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<PollOption> getAll() {
        try (Connection connection = ConnectionManager.open(); PreparedStatement preparedStatement = connection.prepareStatement(PollOptionQueries.SELECT_ALL_POLL_OPTIONS)) {
            List<PollOption> pollOptions = new ArrayList<>();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                UUID id = resultSet.getObject("id", UUID.class);
                UUID pollId = resultSet.getObject("poll_id", UUID.class);
                String description = resultSet.getString("description");

                pollOptions.add(PollOption.builder()
                        .id(id)
                        .pollId(pollId)
                        .description(description)
                        .build()
                );
            }
            return pollOptions;
        } catch (SQLException e) {
            throw new NoPollOptionsFoundException("No poll options found");
        }
    }

    @Override
    public Optional<PollOption> update(PollOption pollOption, UUID pollOptionId) {
        try (Connection connection = ConnectionManager.open(); PreparedStatement preparedStatement = connection.prepareStatement(PollOptionQueries.UPDATE_POLL_OPTION_BY_ID)) {
            preparedStatement.setString(1, pollOption.getDescription());
            preparedStatement.setObject(2, pollOptionId);
            return Optional.ofNullable(getPollOption(preparedStatement));
        } catch (SQLException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<PollOption> delete(UUID pollOptionId) {
        try (Connection connection = ConnectionManager.open(); PreparedStatement preparedStatement = connection.prepareStatement(PollOptionQueries.DELETE_POLL_OPTION_BY_ID)) {
            preparedStatement.setObject(1, pollOptionId);
            return Optional.ofNullable(getPollOption(preparedStatement));
        } catch (SQLException e) {
            return Optional.empty();
        }
    }

    private PollOption getPollOption(PreparedStatement preparedStatement) throws SQLException {
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            UUID id = resultSet.getObject("id", UUID.class);
            UUID pollId = resultSet.getObject("poll_id", UUID.class);
            String description = resultSet.getString("description");

            return PollOption.builder()
                    .id(id)
                    .pollId(pollId)
                    .description(description)
                    .build();
        } else {
            throw new PollOptionNotFoundException("Poll option not found");
        }
    }
}
