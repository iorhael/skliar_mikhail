package com.senla.dto.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class PasswordDto {

    @NotBlank
    @Length(min = 5, max = 32)
    private String password;
}
