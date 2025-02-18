package com.senla.dto.poll;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PollUpdateDto {

    @NotBlank
    private String description;
}
