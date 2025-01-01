package com.senla.dto.poll;

import jakarta.validation.constraints.NotBlank;

public class PollUpdateDto {
    @NotBlank
    private String description;

    public PollUpdateDto() {
    }

    public PollUpdateDto(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
