package com.senla.api.service.imp;

import com.senla.api.dao.UserDao;
import com.senla.api.dao.exception.UserNotFoundException;
import com.senla.api.dto.user.UserCreateDto;
import com.senla.api.dto.user.UserGetDto;
import com.senla.api.model.User;
import com.senla.api.service.UserService;
import com.senla.api.service.exception.user.UserCreateException;
import com.senla.api.service.exception.user.UserDeleteException;
import com.senla.api.service.exception.user.UserUpdateException;
import com.senla.api.util.ModelMapperUtil;
import com.senla.di.annotation.Autowired;
import com.senla.di.annotation.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public UserGetDto createUser(UserCreateDto user) {
        User userEntity = ModelMapperUtil.getConfiguredMapper().map(user, User.class);

        return userDao.create(userEntity)
                .map(u -> ModelMapperUtil.getConfiguredMapper().map(u, UserGetDto.class))
                .orElseThrow(() -> new UserCreateException("Can't create user"));
    }

    @Override
    public UserGetDto getUserById(UUID id) {
        return userDao.getById(id)
                .map(user -> ModelMapperUtil.getConfiguredMapper().map(user, UserGetDto.class))
                .orElseThrow(() -> new UserNotFoundException("No user found"));
    }

    @Override
    public List<UserGetDto> getAllUsers() {
        List<User> users = userDao.getAll();
        List<UserGetDto> userGetDtos = new ArrayList<>();

        for (User user : users) {
            UserGetDto userGetDto = ModelMapperUtil.getConfiguredMapper().map(user, UserGetDto.class);
            userGetDtos.add(userGetDto);
        }

        return userGetDtos;
    }

    @Override
    public UserGetDto updateUser(UserCreateDto user, UUID id) {
        User userEntity = ModelMapperUtil.getConfiguredMapper().map(user, User.class);

        return userDao.update(userEntity, id)
                .map(u -> ModelMapperUtil.getConfiguredMapper().map(u, UserGetDto.class))
                .orElseThrow(() -> new UserUpdateException("Can't update user"));
    }

    @Override
    public UserGetDto deleteUser(UUID id) {
        return userDao.delete(id)
                .map(user -> ModelMapperUtil.getConfiguredMapper().map(user, UserGetDto.class))
                .orElseThrow(() -> new UserDeleteException("Can't delete user"));
    }
}
