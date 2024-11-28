package com.senla.DAO.imp;

import com.senla.DAO.PollOptionDAO;
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

public class PollOptionDAOImpl implements PollOptionDAO {
    private static final String CREATE_POLL_OPTION = "INSERT INTO poll_options (poll_id, description) VALUES (?, ?)";
    private static final String SELECT_POLL_OPTION_BY_ID = "SELECT * FROM poll_options WHERE id = ?";
    private static final String SELECT_ALL_POLL_OPTIONS = "SELECT * FROM poll_options";
    private static final String UPDATE_POLL_OPTION_BY_ID = "UPDATE poll_options SET description = ? WHERE id = ? RETURNING *";
    private static final String DELETE_POLL_OPTION_BY_ID = "DELETE FROM poll_options WHERE id = ? RETURNING *";

    @Override
    public boolean create(PollOption pollOption) {
        try(Connection connection = ConnectionManager.open(); PreparedStatement preparedStatement = connection.prepareStatement(CREATE_POLL_OPTION)) {
            preparedStatement.setObject(1, pollOption.getPollId());
            preparedStatement.setString(2, pollOption.getDescription());
            preparedStatement.executeUpdate();
        } catch (SQLException e){
            return false;
        }
        return true;
    }

    @Override
    public Optional<PollOption> getById(UUID pollOption) {
        try(Connection connection = ConnectionManager.open(); PreparedStatement preparedStatement = connection.prepareStatement(SELECT_POLL_OPTION_BY_ID)){
            preparedStatement.setObject(1, pollOption);
            return Optional.ofNullable(getPollOption(preparedStatement));
        } catch (SQLException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<PollOption> getAll() {
        try(Connection connection = ConnectionManager.open(); PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_POLL_OPTIONS)){
            List<PollOption> pollOptions = new ArrayList<>();
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                UUID id = resultSet.getObject("id", UUID.class);
                UUID pollId = resultSet.getObject("poll_id", UUID.class);
                String description = resultSet.getString("description");

                pollOptions.add(new PollOption.Builder()
                        .id(id)
                        .pollId(pollId)
                        .description(description)
                        .build()
                );
            }
            return pollOptions;
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public Optional<PollOption> update(PollOption pollOption, UUID pollOptionId) {
        try (Connection connection = ConnectionManager.open(); PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_POLL_OPTION_BY_ID)) {
            preparedStatement.setString(1, pollOption.getDescription());
            preparedStatement.setObject(2, pollOptionId);
            return Optional.ofNullable(getPollOption(preparedStatement));
        } catch (SQLException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<PollOption> delete(UUID pollOptionId) {
        try (Connection connection = ConnectionManager.open(); PreparedStatement preparedStatement = connection.prepareStatement(DELETE_POLL_OPTION_BY_ID)) {
            preparedStatement.setObject(1, pollOptionId);
            return Optional.ofNullable(getPollOption(preparedStatement));
        } catch (SQLException e) {
            return Optional.empty();
        }
    }

    private PollOption getPollOption(PreparedStatement preparedStatement) throws SQLException {
        PollOption pollOption = null;
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            UUID id = resultSet.getObject("id", UUID.class);
            UUID pollId = resultSet.getObject("poll_id", UUID.class);
            String description = resultSet.getString("description");

            pollOption = new PollOption.Builder()
                    .id(id)
                    .pollId(pollId)
                    .description(description)
                    .build();
        }
        return pollOption;
    }
}
