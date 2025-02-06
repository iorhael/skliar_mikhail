package com.senla.service.imp;

import com.senla.aspect.Benchmarked;
import com.senla.dto.user.UserCreateDto;
import com.senla.dto.user.UserGetDto;
import com.senla.model.User;
import com.senla.repository.SubscriptionRepository;
import com.senla.repository.UserRepository;
import com.senla.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Benchmarked
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    public static final String USER_NOT_FOUND = "User not found";

    private final UserRepository userRepository;

    private final SubscriptionRepository subscriptionRepository;

    private final ModelMapper modelMapper;

    @Override
    public UserGetDto createUser(UserCreateDto userCreateDto) {
        User user = modelMapper.map(userCreateDto, User.class);

        userRepository.save(user);

        return modelMapper.map(user, UserGetDto.class);
    }

    @Override
    public UserGetDto getUserBy(UUID id) {
        return userRepository.findById(id)
                .map(user -> modelMapper.map(user, UserGetDto.class))
                .orElseThrow(() -> new EntityNotFoundException(USER_NOT_FOUND));
    }

    @Override
    public UserGetDto getUserBy(String email) {
        return userRepository.findByEmail(email)
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
    public List<UserGetDto> getUsersBy(String username) {
        return userRepository.findByUsernameContainingIgnoreCase(username)
                .stream()
                .map(user -> modelMapper.map(user, UserGetDto.class))
                .toList();
    }

    @Override
    @Transactional
    public UserGetDto updateUser(UserCreateDto userCreateDto, UUID id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(USER_NOT_FOUND));

        modelMapper.map(userCreateDto, user);

        return modelMapper.map(user, UserGetDto.class);
    }

    @Override
    public void deleteUser(UUID id) {
        userRepository.deleteById(id);
    }
}
