package com.senla.dto.tag;

import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public class TagDto {
    private UUID id;

    @NotBlank
    private String name;

    public TagDto() {
    }

    public TagDto(String name) {
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
}
