package com.senla.DAO.imp;

import com.senla.DAO.TagDAO;
import com.senla.model.Tag;
import com.senla.util.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class TagDAOImpl implements TagDAO {
    private static final String CREATE_TAG = "INSERT INTO tags (name) VALUES (?)";
    private static final String SELECT_TAG_BY_ID = "SELECT * FROM tags WHERE id = ?";
    private static final String SELECT_ALL_TAGS = "SELECT * FROM tags";
    private static final String UPDATE_TAG_BY_ID = "UPDATE tags SET name = ? WHERE id = ? RETURNING *";
    private static final String DELETE_TAG_BY_ID = "DELETE FROM tags WHERE id = ? RETURNING *";

    @Override
    public boolean create(Tag tag) {
        try(Connection connection = ConnectionManager.open(); PreparedStatement preparedStatement = connection.prepareStatement(CREATE_TAG)) {
            preparedStatement.setString(1, tag.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException e){
            return false;
        }
        return true;
    }

    @Override
    public Optional<Tag> getById(UUID tagId) {
        try(Connection connection = ConnectionManager.open(); PreparedStatement preparedStatement = connection.prepareStatement(SELECT_TAG_BY_ID)){
            preparedStatement.setObject(1, tagId);
            return Optional.ofNullable(getTag(preparedStatement));
        } catch (SQLException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Tag> getAll() {
        try(Connection connection = ConnectionManager.open(); PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_TAGS)){
            List<Tag> tags = new ArrayList<>();
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                UUID id = resultSet.getObject("id", UUID.class);
                String name = resultSet.getString("name");

                tags.add(new Tag.Builder()
                        .id(id)
                        .name(name)
                        .build()
                );
            }
            return tags;
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public Optional<Tag> update(Tag tag, UUID tagId) {
        try (Connection connection = ConnectionManager.open(); PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_TAG_BY_ID)) {
            preparedStatement.setString(1, tag.getName());
            preparedStatement.setObject(2, tagId);
            return Optional.ofNullable(getTag(preparedStatement));
        } catch (SQLException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Tag> delete(UUID tagId) {
        try (Connection connection = ConnectionManager.open(); PreparedStatement preparedStatement = connection.prepareStatement(DELETE_TAG_BY_ID)) {
            preparedStatement.setObject(1, tagId);
            return Optional.ofNullable(getTag(preparedStatement));
        } catch (SQLException e) {
            return Optional.empty();
        }
    }

    private Tag getTag(PreparedStatement preparedStatement) throws SQLException {
        Tag tag = null;
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            UUID id = resultSet.getObject("id", UUID.class);
            String name = resultSet.getString("name");

            tag = new Tag.Builder()
                    .id(id)
                    .name(name)
                    .build();
        }
        return tag;
    }
}
