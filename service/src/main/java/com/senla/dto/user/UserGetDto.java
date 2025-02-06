package com.senla.dto.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
public class UserGetDto {

    private UUID id;

    private String username;

    private String email;
}
