package com.senla.DAO.imp;

import com.senla.DAO.RoleDAO;
import com.senla.model.Role;
import com.senla.model.RoleName;
import com.senla.util.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class RoleDAOImpl implements RoleDAO {
    private static final String CREATE_ROLE = "INSERT INTO roles (user_id, name) VALUES (?, ?)";
    private static final String SELECT_ROLE_BY_ID = "SELECT * FROM roles WHERE id = ?";
    private static final String SELECT_ALL_ROLES = "SELECT * FROM roles";
    private static final String UPDATE_ROLE_BY_ID = "UPDATE roles SET name = ? WHERE id = ? RETURNING *";
    private static final String DELETE_ROLE_BY_ID = "DELETE FROM roles WHERE id = ? RETURNING *";

    @Override
    public boolean create(Role role) {
        try(Connection connection = ConnectionManager.open(); PreparedStatement preparedStatement = connection.prepareStatement(CREATE_ROLE)) {
            preparedStatement.setObject(1, role.getUserId());
            preparedStatement.setObject(2, role.getName(), Types.OTHER);
            preparedStatement.executeUpdate();
        } catch (SQLException e){
            return false;
        }
        return true;
    }

    @Override
    public Optional<Role> getById(UUID roleId) {
        try(Connection connection = ConnectionManager.open(); PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ROLE_BY_ID)){
            preparedStatement.setObject(1, roleId);
            return Optional.ofNullable(getRole(preparedStatement));
        } catch (SQLException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Role> getAll() {
        try(Connection connection = ConnectionManager.open(); PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_ROLES)){
            List<Role> roles = new ArrayList<>();
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                UUID id = resultSet.getObject("id", UUID.class);
                UUID userId = resultSet.getObject("user_id", UUID.class);
                RoleName name = RoleName.valueOf(resultSet.getString("name"));

                roles.add(new Role.Builder()
                        .id(id)
                        .userId(userId)
                        .name(name)
                        .build()
                );
            }
            return roles;
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public Optional<Role> update(Role role, UUID roleId) {
        try (Connection connection = ConnectionManager.open(); PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ROLE_BY_ID)) {
            preparedStatement.setObject(1, role.getName(), Types.OTHER);
            preparedStatement.setObject(2, roleId);
            return Optional.ofNullable(getRole(preparedStatement));
        } catch (SQLException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Role> delete(UUID roleId) {
        try (Connection connection = ConnectionManager.open(); PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ROLE_BY_ID)) {
            preparedStatement.setObject(1, roleId);
            return Optional.ofNullable(getRole(preparedStatement));
        } catch (SQLException e) {
            return Optional.empty();
        }
    }

    private Role getRole(PreparedStatement preparedStatement) throws SQLException {
        Role role = null;
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            UUID id = resultSet.getObject("id", UUID.class);
            UUID userId = resultSet.getObject("user_id", UUID.class);
            RoleName name = RoleName.valueOf(resultSet.getString("name"));

            role = new Role.Builder()
                    .id(id)
                    .userId(userId)
                    .name(name)
                    .build();
        }
        return role;
    }
}
