package com.senla.DAO.imp;

import com.senla.DAO.CategoryDAO;
import com.senla.model.Category;
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

public class CategoryDAOImpl implements CategoryDAO {
    private static final String CREATE_CATEGORY = "INSERT INTO categories (name, description, parent_id) VALUES (?, ?, ?)";
    private static final String SELECT_CATEGORY_BY_ID = "SELECT * FROM categories WHERE id = ?";
    private static final String SELECT_ALL_CATEGORIES = "SELECT * FROM categories";
    private static final String UPDATE_CATEGORY_BY_ID = "UPDATE categories SET name = ?, description = ?, parent_id = ? WHERE id = ? RETURNING *";
    private static final String DELETE_CATEGORY_BY_ID = "DELETE FROM categories WHERE id = ? RETURNING *";

    @Override
    public boolean create(Category category) {
        try(Connection connection = ConnectionManager.open(); PreparedStatement preparedStatement = connection.prepareStatement(CREATE_CATEGORY)) {
            preparedStatement.setString(1, category.getName());
            preparedStatement.setString(2, category.getDescription());
            preparedStatement.setObject(3, category.getParentId(), Types.OTHER);
            preparedStatement.executeUpdate();
        } catch (SQLException e){
            return false;
        }
        return true;
    }

    @Override
    public Optional<Category> getById(UUID categoryId) {
        try(Connection connection = ConnectionManager.open(); PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CATEGORY_BY_ID)){
            preparedStatement.setObject(1, categoryId);
            return Optional.ofNullable(getCategory(preparedStatement));
        } catch (SQLException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Category> getAll() {
        try(Connection connection = ConnectionManager.open(); PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CATEGORIES)){
            List<Category> categories = new ArrayList<>();
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                UUID id = resultSet.getObject("id", UUID.class);
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                UUID parentId = resultSet.getObject("parent_id", UUID.class);

                categories.add(new Category.Builder()
                        .id(id)
                        .name(name)
                        .description(description)
                        .parentId(parentId)
                        .build()
                );
            }
            return categories;
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public Optional<Category> update(Category category, UUID categoryId) {
        try (Connection connection = ConnectionManager.open(); PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CATEGORY_BY_ID)) {
            preparedStatement.setString(1, category.getName());
            preparedStatement.setString(2, category.getDescription());
            preparedStatement.setObject(3, category.getParentId(), Types.OTHER);
            preparedStatement.setObject(4, categoryId);
            return Optional.ofNullable(getCategory(preparedStatement));
        } catch (SQLException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Category> delete(UUID categoryId) {
        try (Connection connection = ConnectionManager.open(); PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CATEGORY_BY_ID)) {
            preparedStatement.setObject(1, categoryId);
            return Optional.ofNullable(getCategory(preparedStatement));
        } catch (SQLException e) {
            return Optional.empty();
        }
    }

    private Category getCategory(PreparedStatement preparedStatement) throws SQLException {
        Category category = null;
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            UUID id = resultSet.getObject("id", UUID.class);
            String name = resultSet.getString("name");
            String description = resultSet.getString("description");
            UUID parentId = resultSet.getObject("parent_id", UUID.class);

            category = new Category.Builder()
                    .id(id)
                    .name(name)
                    .description(description)
                    .parentId(parentId)
                    .build();
        }
        return category;
    }
}
