package com.senla.config.modelmapper;

import com.senla.dto.user.UserWithRolesDto;
import com.senla.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserMappingsConfigurator implements ModelMapperConfigurator {

    @Override
    public void configure(ModelMapper modelMapper) {
        modelMapper.createTypeMap(User.class, UserWithRolesDto.class).addMappings(
                mapper -> mapper.using(ConverterUtil.ROLES_TO_NAMES)
                        .map(User::getRoles, UserWithRolesDto::setRoles)
        );
    }
}
