package com.senla.repository.imp;

import com.senla.di.annotation.Component;
import com.senla.model.Category;
import com.senla.repository.CategoryRepository;
import com.senla.repository.exception.CategoryNotFoundException;
import com.senla.repository.exception.NoCategoriesFoundException;
import com.senla.repository.query.CategoryQueries;
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
public class CategoryRepositoryImpl implements CategoryRepository {
    @Override
    public Optional<Category> create(Category category) {
        try (Connection connection = ConnectionManager.open(); PreparedStatement preparedStatement = connection.prepareStatement(CategoryQueries.CREATE_CATEGORY)) {
            preparedStatement.setString(1, category.getName());
            preparedStatement.setString(2, category.getDescription());
            preparedStatement.setObject(3, category.getParentId(), Types.OTHER);
            return Optional.of(getCategory(preparedStatement));
        } catch (SQLException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Category> getById(UUID categoryId) {
        try (Connection connection = ConnectionManager.open(); PreparedStatement preparedStatement = connection.prepareStatement(CategoryQueries.SELECT_CATEGORY_BY_ID)) {
            preparedStatement.setObject(1, categoryId);
            return Optional.of(getCategory(preparedStatement));
        } catch (SQLException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Category> getAll() {
        try (Connection connection = ConnectionManager.open(); PreparedStatement preparedStatement = connection.prepareStatement(CategoryQueries.SELECT_ALL_CATEGORIES)) {
            List<Category> categories = new ArrayList<>();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                UUID id = resultSet.getObject("id", UUID.class);
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                UUID parentId = resultSet.getObject("parent_id", UUID.class);

                Category category = new Category(name);
                category.setId(id);
                category.setDescription(description);
                category.setParentId(parentId);

                categories.add(category);
            }
            return categories;
        } catch (SQLException e) {
            throw new NoCategoriesFoundException("No categories found");
        }
    }

    @Override
    public Optional<Category> update(Category category, UUID categoryId) {
        try (Connection connection = ConnectionManager.open(); PreparedStatement preparedStatement = connection.prepareStatement(CategoryQueries.UPDATE_CATEGORY_BY_ID)) {
            preparedStatement.setString(1, category.getName());
            preparedStatement.setString(2, category.getDescription());
            preparedStatement.setObject(3, category.getParentId(), Types.OTHER);
            preparedStatement.setObject(4, categoryId);
            return Optional.of(getCategory(preparedStatement));
        } catch (SQLException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Category> delete(UUID categoryId) {
        try (Connection connection = ConnectionManager.open(); PreparedStatement preparedStatement = connection.prepareStatement(CategoryQueries.DELETE_CATEGORY_BY_ID)) {
            preparedStatement.setObject(1, categoryId);
            return Optional.of(getCategory(preparedStatement));
        } catch (SQLException e) {
            return Optional.empty();
        }
    }

    private Category getCategory(PreparedStatement preparedStatement) throws SQLException {
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            UUID id = resultSet.getObject("id", UUID.class);
            String name = resultSet.getString("name");
            String description = resultSet.getString("description");
            UUID parentId = resultSet.getObject("parent_id", UUID.class);

            Category category = new Category(name);
            category.setId(id);
            category.setDescription(description);
            category.setParentId(parentId);

            return category;
        } else {
            throw new CategoryNotFoundException("Category not found");
        }
    }
}
