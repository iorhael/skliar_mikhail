package com.senla.dto.category;

import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public class CategoryCreateDto {
    @NotBlank
    private String name;

    @NotBlank
    private String description;

    private UUID parentId;

    public CategoryCreateDto() {
    }

    public CategoryCreateDto(String name, String description, UUID parentId) {
        this.name = name;
        this.description = description;
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
