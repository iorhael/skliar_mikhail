package com.senla.api.dao.imp;

import com.senla.api.dao.TagDao;
import com.senla.api.dao.exception.NoTagsFoundException;
import com.senla.api.dao.exception.TagNotFoundException;
import com.senla.api.dao.query.TagQueries;
import com.senla.api.model.Tag;
import com.senla.api.util.ConnectionManager;
import com.senla.di.annotation.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class TagDaoImpl implements TagDao {
    @Override
    public Optional<Tag> create(Tag tag) {
        try (Connection connection = ConnectionManager.open(); PreparedStatement preparedStatement = connection.prepareStatement(TagQueries.CREATE_TAG)) {
            preparedStatement.setString(1, tag.getName());
            return Optional.ofNullable(getTag(preparedStatement));
        } catch (SQLException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Tag> getById(UUID tagId) {
        try (Connection connection = ConnectionManager.open(); PreparedStatement preparedStatement = connection.prepareStatement(TagQueries.SELECT_TAG_BY_ID)) {
            preparedStatement.setObject(1, tagId);
            return Optional.ofNullable(getTag(preparedStatement));
        } catch (SQLException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Tag> getAll() {
        try (Connection connection = ConnectionManager.open(); PreparedStatement preparedStatement = connection.prepareStatement(TagQueries.SELECT_ALL_TAGS)) {
            List<Tag> tags = new ArrayList<>();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                UUID id = resultSet.getObject("id", UUID.class);
                String name = resultSet.getString("name");

                tags.add(Tag.builder()
                        .id(id)
                        .name(name)
                        .build()
                );
            }
            return tags;
        } catch (SQLException e) {
            throw new NoTagsFoundException("No tags found");
        }
    }

    @Override
    public Optional<Tag> update(Tag tag, UUID tagId) {
        try (Connection connection = ConnectionManager.open(); PreparedStatement preparedStatement = connection.prepareStatement(TagQueries.UPDATE_TAG_BY_ID)) {
            preparedStatement.setString(1, tag.getName());
            preparedStatement.setObject(2, tagId);
            return Optional.ofNullable(getTag(preparedStatement));
        } catch (SQLException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Tag> delete(UUID tagId) {
        try (Connection connection = ConnectionManager.open(); PreparedStatement preparedStatement = connection.prepareStatement(TagQueries.DELETE_TAG_BY_ID)) {
            preparedStatement.setObject(1, tagId);
            return Optional.ofNullable(getTag(preparedStatement));
        } catch (SQLException e) {
            return Optional.empty();
        }
    }

    private Tag getTag(PreparedStatement preparedStatement) throws SQLException {
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            UUID id = resultSet.getObject("id", UUID.class);
            String name = resultSet.getString("name");

            return Tag.builder()
                    .id(id)
                    .name(name)
                    .build();
        } else {
            throw new TagNotFoundException("Tag not found");
        }
    }
}
