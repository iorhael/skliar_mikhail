package com.senla.api.dto.poll;

import com.senla.api.util.ValidationUtil;

public class PollUpdateDto {
    private String description;

    public PollUpdateDto() {
    }

    public PollUpdateDto(String description) {
        this.description = ValidationUtil.validateNotNullOrEmpty(description);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
