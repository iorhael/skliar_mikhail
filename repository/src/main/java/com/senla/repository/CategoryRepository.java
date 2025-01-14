package com.senla.repository;

import com.senla.di.annotation.Component;
import com.senla.model.Category;
import com.senla.util.EntityManagerUtil;
import jakarta.persistence.EntityManager;

import java.util.Optional;
import java.util.UUID;

@Component
public class CategoryRepository extends BaseRepository<Category, UUID> {
    public CategoryRepository() {
        super(Category.class);
    }

    @Override
    public Optional<Category> update(Category category, UUID id) {
        try (EntityManager entityManager = EntityManagerUtil.getEntityManager()) {
            entityManager.getTransaction().begin();

            Category existingCategory = entityManager.find(Category.class, id);

            if (existingCategory != null) {
                existingCategory.setName(category.getName());
                existingCategory.setDescription(category.getDescription());
            }

            entityManager.getTransaction().commit();

            return Optional.ofNullable(existingCategory);
        }
    }
}
