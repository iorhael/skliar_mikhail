package com.senla.repository.imp;

import com.senla.di.annotation.Component;
import com.senla.model.User;
import com.senla.repository.UserRepository;
import com.senla.repository.exception.NoUsersFoundException;
import com.senla.repository.exception.UserNotFoundException;
import com.senla.repository.query.UserQueries;
import com.senla.util.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class UserRepositoryImpl implements UserRepository {
    @Override
    public Optional<User> create(User user) {
        try (Connection connection = ConnectionManager.open(); PreparedStatement preparedStatement = connection.prepareStatement(UserQueries.CREATE_USER)) {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
            return Optional.of(getUser(preparedStatement));
        } catch (SQLException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<User> getById(UUID userId) {
        try (Connection connection = ConnectionManager.open(); PreparedStatement preparedStatement = connection.prepareStatement(UserQueries.SELECT_USER_BY_ID)) {
            preparedStatement.setObject(1, userId);
            return Optional.of(getUser(preparedStatement));
        } catch (SQLException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        try (Connection connection = ConnectionManager.open(); PreparedStatement preparedStatement = connection.prepareStatement(UserQueries.SELECT_ALL_USERS)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                UUID id = resultSet.getObject("id", UUID.class);
                String username = resultSet.getString("username");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                LocalDateTime createdDate = resultSet.getTimestamp("created_date").toLocalDateTime();

                User user = new User(username, email, password);
                user.setId(id);
                user.setCreatedDate(createdDate);

                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            throw new NoUsersFoundException("No users found");
        }
    }

    @Override
    public Optional<User> update(User user, UUID userId) {
        try (Connection connection = ConnectionManager.open(); PreparedStatement preparedStatement = connection.prepareStatement(UserQueries.UPDATE_USER_BY_ID)) {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setObject(4, userId);
            return Optional.of(getUser(preparedStatement));
        } catch (SQLException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<User> delete(UUID userId) {
        try (Connection connection = ConnectionManager.open(); PreparedStatement preparedStatement = connection.prepareStatement(UserQueries.DELETE_USER_BY_ID)) {
            preparedStatement.setObject(1, userId);
            return Optional.of(getUser(preparedStatement));
        } catch (SQLException e) {
            return Optional.empty();
        }
    }

    private User getUser(PreparedStatement preparedStatement) throws SQLException {
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            UUID id = resultSet.getObject("id", UUID.class);
            String username = resultSet.getString("username");
            String email = resultSet.getString("email");
            String password = resultSet.getString("password");
            LocalDateTime createdDate = resultSet.getTimestamp("created_date").toLocalDateTime();

            User user = new User(username, email, password);
            user.setId(id);
            user.setCreatedDate(createdDate);

            return user;
        } else {
            throw new UserNotFoundException("User not found");
        }
    }
}
