package com.senla.dto.user;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdateDto {

    @NotNull
    @Pattern(regexp = "^[A-Za-z0-9_.]+$",
            message = "Only latin letters, numbers, underscores and periods are allowed")
    private String username;

    @NotNull
    @Pattern(regexp = "^[a-zA-Z0-9.]+@[a-zA-Z0-9.]+\\.[a-zA-Z]{2,}$",
            message = "Invalid email format")
    private String email;
}
