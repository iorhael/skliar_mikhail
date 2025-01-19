package com.senla.service.imp;

import com.senla.dto.user.UserCreateDto;
import com.senla.dto.user.UserGetDto;
import com.senla.model.User;
import com.senla.repository.UserRepository;
import com.senla.service.UserService;
import com.senla.service.exception.ServiceException;
import com.senla.service.exception.user.UserDeleteException;
import com.senla.service.exception.user.UserUpdateException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    @Override
    public UserGetDto createUser(UserCreateDto user) {
        User userEntity = modelMapper.map(user, User.class);

        User createdUser = userRepository.create(userEntity);

        return modelMapper.map(createdUser, UserGetDto.class);
    }

    @Transactional
    @Override
    public UserGetDto getUserById(UUID id) {
        return userRepository.findById(id)
                .map(user -> modelMapper.map(user, UserGetDto.class))
                .orElseThrow(() -> new ServiceException("No user found"));
    }

    @Transactional
    @Override
    public List<UserGetDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(user -> modelMapper.map(user, UserGetDto.class))
                .toList();
    }

    @Transactional
    @Override
    public UserGetDto updateUser(UserCreateDto user, UUID id) {
        User userEntity = modelMapper.map(user, User.class);

        return userRepository.update(userEntity, id)
                .map(u -> modelMapper.map(u, UserGetDto.class))
                .orElseThrow(() -> new UserUpdateException("Can't update user"));
    }

    @Transactional
    @Override
    public UserGetDto deleteUser(UUID id) {
        return userRepository.deleteById(id)
                .map(user -> modelMapper.map(user, UserGetDto.class))
                .orElseThrow(() -> new UserDeleteException("Can't delete user"));
    }
}
