package com.senla.api.dto.category;

import com.senla.api.util.ValidationUtil;

import java.util.UUID;

public class CategoryCreateDto {
    private String name;
    private String description;
    private UUID parentId;

    public CategoryCreateDto() {
    }

    public CategoryCreateDto(String name, String description, UUID parentId) {
        this.name = ValidationUtil.validateNotNullOrEmpty(name);
        this.description = ValidationUtil.validateNotNullOrEmpty(description);
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UUID getParentId() {
        return parentId;
    }

    public void setParentId(UUID parentId) {
        this.parentId = parentId;
    }
}
