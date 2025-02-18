package com.senla.dto.user;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
public class UserWithRolesDto {

    private UUID id;

    private String username;

    private String email;

    private Set<String> roles;
}
