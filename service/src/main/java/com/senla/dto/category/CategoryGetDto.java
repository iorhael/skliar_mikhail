package com.senla.dto.category;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CategoryGetDto {

    private UUID id;

    private String name;

    private String description;
}
