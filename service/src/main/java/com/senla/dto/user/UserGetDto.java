package com.senla.dto.user;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UserGetDto {

    private UUID id;

    private String username;

    private String email;
}
