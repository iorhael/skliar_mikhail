package com.senla.repository;

import com.senla.util.SessionManager;
import org.hibernate.Session;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public abstract class BaseRepository<T, I extends Serializable> {
    private final Class<T> entityClass;

    protected BaseRepository(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public T create(T entity) {
        try (Session session = SessionManager.openSession()) {
            session.beginTransaction();

            session.persist(entity);

            session.getTransaction().commit();
        }
        return entity;
    }

    public Optional<T> findById(I id) {
        try (Session session = SessionManager.openSession()) {
            T entity = session.get(entityClass, id);
            return Optional.ofNullable(entity);
        }
    }

    public List<T> findAll() {
        try (Session session = SessionManager.openSession()) {
            return session.createQuery("FROM " + entityClass.getSimpleName(), entityClass).list();
        }
    }

    public Optional<T> deleteById(I id) {
        Optional<T> result = Optional.empty();

        try (Session session = SessionManager.openSession()) {
            session.beginTransaction();

            T entity = session.get(entityClass, id);

            if (entity != null) {
                result = Optional.of(entity);
                session.remove(entity);
            }

            session.getTransaction().commit();
        }
        return result;
    }

    public abstract Optional<T> update(T entity, I id);
}
