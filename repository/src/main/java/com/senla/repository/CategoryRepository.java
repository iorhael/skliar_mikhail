package com.senla.repository;

import com.senla.model.Category;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class CategoryRepository extends BaseRepository<Category, UUID> {

    public CategoryRepository() {
        super(Category.class);
    }

    @Override
    public Category create(Category category) {
        UUID parentCategoryId = category.getParentCategory().getId();
        Category persistedParentCategory = (parentCategoryId != null) ? entityManager.getReference(Category.class, parentCategoryId) : null;

        category.setParentCategory(persistedParentCategory);

        entityManager.persist(category);

        return category;
    }

    @Override
    public Optional<Category> update(Category category, UUID id) {
        Category existingCategory = entityManager.find(Category.class, id);

        if (existingCategory != null) {
            existingCategory.setName(category.getName());
            existingCategory.setDescription(category.getDescription());
        }

        return Optional.ofNullable(existingCategory);
    }
}
