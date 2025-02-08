package com.senla.dto.category;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
public class CategoryCreateDto {

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    private UUID parentId;
}
