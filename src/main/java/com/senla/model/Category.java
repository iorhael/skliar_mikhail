package com.senla.model;

import com.senla.util.ValidationUtil;

import java.util.UUID;

public class Category {
    private final UUID id;
    private final String name;
    private final String description;
    private final UUID parentId;

    Category(Builder builder) {
        this.id = builder.id;
        this.name = ValidationUtil.validateNotNullOrEmpty(builder.name);
        this.description = builder.description;
        this.parentId = builder.parentId;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public UUID getParentId() {
        return parentId;
    }

    public static class Builder {
        private UUID id;
        private String name;
        private String description;
        private UUID parentId;

        public Builder id(UUID id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

       public Builder parentId(UUID parentId) {
            this.parentId = parentId;
            return this;
       }

        public Category build() {
            return new Category(this);
        }
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", parentId=" + parentId +
                '}';
    }
}
