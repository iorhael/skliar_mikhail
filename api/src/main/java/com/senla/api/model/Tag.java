package com.senla.api.model;

import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public class Tag {
    private UUID id;

    @NotBlank
    private String name;

    public Tag() {
    }

    public Tag(String name) {
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
