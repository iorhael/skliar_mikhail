package com.senla.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreateDto {

    @NotNull
    @Pattern(regexp = "^[A-Za-z0-9_.]+$",
            message = "Only latin letters, numbers, underscores and periods are allowed")
    private String username;

    @NotNull
    @Pattern(regexp = "^[a-zA-Z0-9.]+@[a-zA-Z0-9.]+\\.[a-zA-Z]{2,}$",
            message = "Invalid email format")
    private String email;

    @NotBlank
    @Size(min = 5, max = 32)
    private String password;
}
