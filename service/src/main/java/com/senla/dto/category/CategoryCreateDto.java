package com.senla.dto.category;

import com.senla.model.Category;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CategoryCreateDto {

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    private Category parentCategory;
}
