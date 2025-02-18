package com.senla.dto.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class PasswordUpdateDto {

    @NotBlank
    @Length(min = 5, max = 32)
    private String oldPassword;

    @NotBlank
    @Length(min = 5, max = 32)
    private String newPassword;
}
