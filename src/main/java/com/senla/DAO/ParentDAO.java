package com.senla.DAO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ParentDAO<T> {
    boolean create(T entity);

    Optional<T> getById(UUID entityId);

    List<T> getAll();

    Optional<T> update(T entity, UUID entityId);

    Optional<T> delete(UUID entityId);
}
