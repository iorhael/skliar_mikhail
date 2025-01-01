package com.senla.repository;

import java.util.List;
import java.util.Optional;

public interface ParentRepository<T, U> {
    Optional<T> create(T entity);

    Optional<T> getById(U entityId);

    List<T> getAll();

    Optional<T> update(T entity, U entityId);

    Optional<T> delete(U entityId);
}
