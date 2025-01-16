package com.senla.service.imp;

import com.senla.di.annotation.Autowired;
import com.senla.di.annotation.Component;
import com.senla.dto.user.UserCreateDto;
import com.senla.dto.user.UserGetDto;
import com.senla.model.User;
import com.senla.repository.UserRepository;
import com.senla.service.UserService;
import com.senla.service.exception.ServiceException;
import com.senla.service.exception.user.UserDeleteException;
import com.senla.service.exception.user.UserUpdateException;
import com.senla.util.ModelMapperUtil;

import java.util.List;
import java.util.UUID;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserGetDto createUser(UserCreateDto user) {
        User userEntity = ModelMapperUtil.MODEL_MAPPER.map(user, User.class);
        User createdUser = userRepository.create(userEntity);

        return ModelMapperUtil.MODEL_MAPPER.map(createdUser, UserGetDto.class);
    }

    @Override
    public UserGetDto getUserById(UUID id) {
        return userRepository.findById(id)
                .map(user -> ModelMapperUtil.MODEL_MAPPER.map(user, UserGetDto.class))
                .orElseThrow(() -> new ServiceException("No user found"));
    }

    @Override
    public List<UserGetDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(user -> ModelMapperUtil.MODEL_MAPPER.map(user, UserGetDto.class))
                .toList();
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
        return userRepository.deleteById(id)
                .map(user -> ModelMapperUtil.MODEL_MAPPER.map(user, UserGetDto.class))
                .orElseThrow(() -> new UserDeleteException("Can't delete user"));
    }
}
