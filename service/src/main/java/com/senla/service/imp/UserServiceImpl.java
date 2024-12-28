package com.senla.service.imp;

import com.senla.di.annotation.Autowired;
import com.senla.di.annotation.Component;
import com.senla.dto.user.UserCreateDto;
import com.senla.dto.user.UserGetDto;
import com.senla.model.User;
import com.senla.repository.UserRepository;
import com.senla.repository.exception.UserNotFoundException;
import com.senla.service.UserService;
import com.senla.service.exception.user.UserCreateException;
import com.senla.service.exception.user.UserDeleteException;
import com.senla.service.exception.user.UserUpdateException;
import com.senla.util.ModelMapperUtil;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserGetDto createUser(UserCreateDto user) {
        User userEntity = ModelMapperUtil.MODEL_MAPPER.map(user, User.class);

        return userRepository.create(userEntity)
                .map(u -> ModelMapperUtil.MODEL_MAPPER.map(u, UserGetDto.class))
                .orElseThrow(() -> new UserCreateException("Can't create user"));
    }

    @Override
    public UserGetDto getUserById(UUID id) {
        return userRepository.getById(id)
                .map(user -> ModelMapperUtil.MODEL_MAPPER.map(user, UserGetDto.class))
                .orElseThrow(() -> new UserNotFoundException("No user found"));
    }

    @Override
    public List<UserGetDto> getAllUsers() {
        return userRepository.getAll().stream()
                .map(user -> ModelMapperUtil.MODEL_MAPPER.map(user, UserGetDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserGetDto updateUser(UserCreateDto user, UUID id) {
        User userEntity = ModelMapperUtil.MODEL_MAPPER.map(user, User.class);

        return userRepository.update(userEntity, id)
                .map(u -> ModelMapperUtil.MODEL_MAPPER.map(u, UserGetDto.class))
                .orElseThrow(() -> new UserUpdateException("Can't update user"));
    }

    @Override
    public UserGetDto deleteUser(UUID id) {
        return userRepository.delete(id)
                .map(user -> ModelMapperUtil.MODEL_MAPPER.map(user, UserGetDto.class))
                .orElseThrow(() -> new UserDeleteException("Can't delete user"));
    }
}
