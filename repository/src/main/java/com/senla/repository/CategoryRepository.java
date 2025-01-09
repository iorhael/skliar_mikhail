package com.senla.repository;

import com.senla.di.annotation.Component;
import com.senla.model.Category;
import com.senla.util.SessionManager;
import org.hibernate.Session;

import java.util.Optional;
import java.util.UUID;

@Component
public class CategoryRepository extends BaseRepository<Category, UUID> {
    public CategoryRepository() {
        super(Category.class);
    }

    @Override
    public Optional<Category> update(Category category, UUID id) {
        Optional<Category> result = Optional.empty();

        try (Session session = SessionManager.openSession()) {
            session.beginTransaction();

            Category existingCategory = session.get(Category.class, id);

            if (existingCategory != null) {
                existingCategory.setName(category.getName());
                existingCategory.setDescription(category.getDescription());

                result = Optional.of(existingCategory);
            }

            session.getTransaction().commit();
        }
        return result;
    }
}
