package com.senla.dao.imp;

import com.senla.dao.UserDao;
import com.senla.dao.exception.NoUsersFoundException;
import com.senla.dao.exception.UserNotFoundException;
import com.senla.dao.query.UserQueries;
import com.senla.model.User;
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

public class UserDaoImpl implements UserDao {
    @Override
    public Optional<User> create(User user) {
        try(Connection connection = ConnectionManager.open(); PreparedStatement preparedStatement = connection.prepareStatement(UserQueries.CREATE_USER)){
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
            return Optional.ofNullable(getUser(preparedStatement));
        } catch (SQLException e){
            return Optional.empty();
        }
    }

    @Override
    public Optional<User> getById(UUID userId) {
        try(Connection connection = ConnectionManager.open(); PreparedStatement preparedStatement = connection.prepareStatement(UserQueries.SELECT_USER_BY_ID)){
            preparedStatement.setObject(1, userId);
            return Optional.ofNullable(getUser(preparedStatement));
        } catch (SQLException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        try(Connection connection = ConnectionManager.open(); PreparedStatement preparedStatement = connection.prepareStatement(UserQueries.SELECT_ALL_USERS)){
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                UUID id = resultSet.getObject("id", UUID.class);
                String username = resultSet.getString("username");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                LocalDateTime createdDate = resultSet.getTimestamp("created_date").toLocalDateTime();

                users.add(User.builder()
                        .id(id)
                        .username(username)
                        .email(email)
                        .password(password)
                        .createdDate(createdDate)
                        .build()
                );
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
            return Optional.ofNullable(getUser(preparedStatement));
        } catch (SQLException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<User> delete(UUID userId) {
        try (Connection connection = ConnectionManager.open(); PreparedStatement preparedStatement = connection.prepareStatement(UserQueries.DELETE_USER_BY_ID)) {
           preparedStatement.setObject(1, userId);
           return Optional.ofNullable(getUser(preparedStatement));
        } catch (SQLException e) {
            return Optional.empty();
        }
    }

    private User getUser(PreparedStatement preparedStatement) throws SQLException {
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            UUID id = resultSet.getObject("id", UUID.class);
            String username = resultSet.getString("username");
            String email = resultSet.getString("email");
            String password = resultSet.getString("password");
            LocalDateTime createdDate = resultSet.getTimestamp("created_date").toLocalDateTime();

            return User.builder()
                    .id(id)
                    .username(username)
                    .email(email)
                    .password(password)
                    .createdDate(createdDate)
                    .build();
        } else {
            throw new UserNotFoundException("User not found");
        }
    }
}
