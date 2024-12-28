package com.senla.repository.imp;

import com.senla.di.annotation.Component;
import com.senla.model.Role;
import com.senla.model.RoleName;
import com.senla.repository.RoleRepository;
import com.senla.repository.exception.NoRolesFoundException;
import com.senla.repository.exception.RoleNotFoundException;
import com.senla.repository.query.RoleQueries;
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

@Component
public class RoleRepositoryImpl implements RoleRepository {
    @Override
    public Optional<Role> create(Role role) {
        try (Connection connection = ConnectionManager.open(); PreparedStatement preparedStatement = connection.prepareStatement(RoleQueries.CREATE_ROLE)) {
            preparedStatement.setObject(1, role.getUserId());
            preparedStatement.setObject(2, role.getName(), Types.OTHER);
            return Optional.of(getRole(preparedStatement));
        } catch (SQLException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Role> getById(UUID roleId) {
        try (Connection connection = ConnectionManager.open(); PreparedStatement preparedStatement = connection.prepareStatement(RoleQueries.SELECT_ROLE_BY_ID)) {
            preparedStatement.setObject(1, roleId);
            return Optional.of(getRole(preparedStatement));
        } catch (SQLException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Role> getAll() {
        try (Connection connection = ConnectionManager.open(); PreparedStatement preparedStatement = connection.prepareStatement(RoleQueries.SELECT_ALL_ROLES)) {
            List<Role> roles = new ArrayList<>();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                UUID id = resultSet.getObject("id", UUID.class);
                UUID userId = resultSet.getObject("user_id", UUID.class);
                RoleName name = RoleName.valueOf(resultSet.getString("name"));

                Role role = new Role(userId, name);
                role.setId(id);

                roles.add(role);
            }
            return roles;
        } catch (SQLException e) {
            throw new NoRolesFoundException("No roles found");
        }
    }

    @Override
    public Optional<Role> update(Role role, UUID roleId) {
        try (Connection connection = ConnectionManager.open(); PreparedStatement preparedStatement = connection.prepareStatement(RoleQueries.UPDATE_ROLE_BY_ID)) {
            preparedStatement.setObject(1, role.getName(), Types.OTHER);
            preparedStatement.setObject(2, roleId);
            return Optional.of(getRole(preparedStatement));
        } catch (SQLException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Role> delete(UUID roleId) {
        try (Connection connection = ConnectionManager.open(); PreparedStatement preparedStatement = connection.prepareStatement(RoleQueries.DELETE_ROLE_BY_ID)) {
            preparedStatement.setObject(1, roleId);
            return Optional.of(getRole(preparedStatement));
        } catch (SQLException e) {
            return Optional.empty();
        }
    }

    private Role getRole(PreparedStatement preparedStatement) throws SQLException {
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            UUID id = resultSet.getObject("id", UUID.class);
            UUID userId = resultSet.getObject("user_id", UUID.class);
            RoleName name = RoleName.valueOf(resultSet.getString("name"));

            Role role = new Role(userId, name);
            role.setId(id);

            return role;
        } else {
            throw new RoleNotFoundException("Role not found");
        }
    }
}
