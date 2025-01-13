package com.senla.repository;

import com.senla.util.EntityManagerUtil;
import jakarta.persistence.EntityManager;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public abstract class BaseRepository<T, I extends Serializable> {
    private final Class<T> entityClass;

    protected BaseRepository(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public T create(T entity) {
        try (EntityManager entityManager = EntityManagerUtil.getEntityManager()) {
            entityManager.getTransaction().begin();

            entityManager.persist(entity);

            entityManager.getTransaction().commit();
        }
        return entity;
    }

    public Optional<T> findById(I id) {
        try (EntityManager entityManager = EntityManagerUtil.getEntityManager()) {
            T entity = entityManager.find(entityClass, id);
            return Optional.ofNullable(entity);
        }
    }

    public List<T> findAll() {
        try (EntityManager entityManager = EntityManagerUtil.getEntityManager()) {
            return entityManager.createQuery("FROM " + entityClass.getSimpleName(), entityClass).getResultList();
        }
    }

    public Optional<T> deleteById(I id) {
        try (EntityManager entityManager = EntityManagerUtil.getEntityManager()) {
            entityManager.getTransaction().begin();

            T entity = entityManager.find(entityClass, id);

            if (entity != null) {
                entityManager.remove(entity);
            }

            entityManager.getTransaction().commit();

            return Optional.ofNullable(entity);
        }
    }

    public abstract Optional<T> update(T entity, I id);
}
