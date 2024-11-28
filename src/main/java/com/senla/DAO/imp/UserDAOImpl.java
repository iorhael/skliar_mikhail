package com.senla.DAO.imp;

import com.senla.DAO.UserDAO;
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

public class UserDAOImpl implements UserDAO {
    private static final String CREATE_USER = "INSERT INTO users (username, email, password) VALUES (?, ?, ?)";
    private static final String SELECT_USER_BY_ID = "SELECT * FROM users WHERE id = ?";
    private static final String SELECT_ALL_USERS = "SELECT * FROM users";
    private static final String UPDATE_USER_BY_ID = "UPDATE users SET username = ?, email = ?, password = ? WHERE id = ? RETURNING *";
    private static final String DELETE_USER_BY_ID = "DELETE FROM users WHERE id = ? RETURNING *";

    @Override
    public boolean create(User user) {
        try(Connection connection = ConnectionManager.open(); PreparedStatement preparedStatement = connection.prepareStatement(CREATE_USER)){
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.executeUpdate();
        } catch (SQLException e){
            return false;
        }
        return true;
    }

    @Override
    public Optional<User> getById(UUID userId) {
        try(Connection connection = ConnectionManager.open(); PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID)){
            preparedStatement.setObject(1, userId);
            return Optional.ofNullable(getUser(preparedStatement));
        } catch (SQLException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        try(Connection connection = ConnectionManager.open(); PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS)){
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                UUID id = resultSet.getObject("id", UUID.class);
                String username = resultSet.getString("username");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                LocalDateTime createdDate = resultSet.getTimestamp("created_date").toLocalDateTime();

                users.add(new User.Builder()
                        .id(id)
                        .username(username)
                        .email(email)
                        .password(password)
                        .createdDate(createdDate)
                        .build()
                );
            }
        } catch (SQLException e) {
            return null;
        }
        return users;
    }

    @Override
    public Optional<User> update(User user, UUID userId) {
        try (Connection connection = ConnectionManager.open(); PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_BY_ID)) {
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
        try (Connection connection = ConnectionManager.open(); PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER_BY_ID)) {
           preparedStatement.setObject(1, userId);
           return Optional.ofNullable(getUser(preparedStatement));
        } catch (SQLException e) {
            return Optional.empty();
        }
    }

    private User getUser(PreparedStatement preparedStatement) throws SQLException {
        User user = null;
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            UUID id = resultSet.getObject("id", UUID.class);
            String username = resultSet.getString("username");
            String email = resultSet.getString("email");
            String password = resultSet.getString("password");
            LocalDateTime createdDate = resultSet.getTimestamp("created_date").toLocalDateTime();

            user = new User.Builder()
                    .id(id)
                    .username(username)
                    .email(email)
                    .password(password)
                    .createdDate(createdDate)
                    .build();
        }
        return user;
    }
}
