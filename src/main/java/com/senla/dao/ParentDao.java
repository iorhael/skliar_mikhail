package com.senla.dao;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ParentDao<T> {
    Optional<T> create(T entity);

    Optional<T> getById(UUID entityId);

    List<T> getAll();

    Optional<T> update(T entity, UUID entityId);

    Optional<T> delete(UUID entityId);
}
