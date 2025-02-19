package com.senla.dto;

import com.senla.validation.annotation.PresenceOfOnlyOne;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@PresenceOfOnlyOne(fields = {"username", "email"},
        message = "Only username or email must be present")
public class LoginDto {

    @Pattern(regexp = "^[A-Za-z0-9_.]+$",
            message = "Only latin letters, numbers, underscores and periods are allowed")
    private String username;

    @Pattern(regexp = "^[a-zA-Z0-9.]+@[a-zA-Z0-9.]+\\.[a-zA-Z]{2,}$",
            message = "Invalid email format")
    private String email;

    @Size(min = 5, max = 32)
    private String password;
}
