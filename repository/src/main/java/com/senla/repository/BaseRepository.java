package com.senla.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public abstract class BaseRepository<T, I extends Serializable> {

    private final Class<T> entityClass;

    @PersistenceContext
    protected EntityManager entityManager;

    protected BaseRepository(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public T create(T entity) {
        entityManager.persist(entity);

        return entity;
    }

    public Optional<T> findById(I id) {
        T entity = entityManager.find(entityClass, id);
        return Optional.ofNullable(entity);
    }

    public List<T> findAll() {
        return entityManager.createQuery("FROM " + entityClass.getSimpleName(), entityClass).getResultList();
    }

    public Optional<T> deleteById(I id) {
        T entity = entityManager.find(entityClass, id);

        if (entity != null) {
            entityManager.remove(entity);
        }

        return Optional.ofNullable(entity);
    }

    public abstract Optional<T> update(T entity, I id);
}
