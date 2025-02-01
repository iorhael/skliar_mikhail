package com.senla.service.imp;

import com.senla.dto.user.UserCreateDto;
import com.senla.dto.user.UserGetDto;
import com.senla.model.User;
import com.senla.repository.UserRepository;
import com.senla.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    public static final String USER_NOT_FOUND = "User not found";

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    @Override
    public UserGetDto createUser(UserCreateDto userCreateDto) {
        User user = modelMapper.map(userCreateDto, User.class);

        log.info("Trying to create user with email {}", user.getEmail());

        userRepository.save(user);

        log.info("User with id {} created successfully", user.getId());

        return modelMapper.map(user, UserGetDto.class);
    }

    @Override
    public UserGetDto getUserById(UUID id) {
        return userRepository.findById(id)
                .map(user -> modelMapper.map(user, UserGetDto.class))
                .orElseThrow(() -> new EntityNotFoundException(USER_NOT_FOUND));
    }

    @Override
    public List<UserGetDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(user -> modelMapper.map(user, UserGetDto.class))
                .toList();
    }

    @Override
    @Transactional
    public UserGetDto updateUser(UserCreateDto userCreateDto, UUID id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(USER_NOT_FOUND));

        user.setUsername(userCreateDto.getUsername());
        user.setEmail(userCreateDto.getEmail());
        user.setPassword(userCreateDto.getPassword());

        return modelMapper.map(user, UserGetDto.class);
    }

    @Override
    public void deleteUser(UUID id) {
        userRepository.deleteById(id);
    }
}
