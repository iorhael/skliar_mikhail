package com.senla.dto.tag;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class TagDto {

    private UUID id;

    @NotBlank
    private String name;
}
