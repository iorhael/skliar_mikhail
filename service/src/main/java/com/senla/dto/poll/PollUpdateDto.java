package com.senla.dto.poll;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PollUpdateDto {

    @NotBlank
    private String description;
}
