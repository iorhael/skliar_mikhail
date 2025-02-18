package com.senla.service.imp;

import com.senla.aspect.Benchmarked;
import com.senla.dto.user.PasswordUpdateDto;
import com.senla.dto.user.UserCreateDto;
import com.senla.dto.user.UserGetDto;
import com.senla.dto.user.UserUpdateDto;
import com.senla.dto.user.UserWithRolesDto;
import com.senla.model.Role;
import com.senla.model.RoleName;
import com.senla.model.User;
import com.senla.repository.UserRepository;
import com.senla.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Benchmarked
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    public static final String USER_NOT_FOUND = "User not found";
    public static final String PASSWORD_MISMATCH = "Wrong password";

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public UserWithRolesDto createUser(UserCreateDto userCreateDto) {
        User user = modelMapper.map(userCreateDto, User.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Role defaultRole = new Role();
        defaultRole.setName(RoleName.READER);

        user.addRole(defaultRole);

        userRepository.save(user);

        return modelMapper.map(user, UserWithRolesDto.class);
    }

    @Override
    public UserGetDto getUserBy(UUID id) {
        return userRepository.findById(id)
                .map(user -> modelMapper.map(user, UserGetDto.class))
                .orElseThrow(() -> new EntityNotFoundException(USER_NOT_FOUND));
    }

    @Override
    public List<UserGetDto> getAllUsers(int pageNo, int pageSize) {
        return userRepository.findAll(PageRequest.of(pageNo, pageSize))
                .stream()
                .map(user -> modelMapper.map(user, UserGetDto.class))
                .toList();
    }

    @Override
    public List<UserGetDto> getUsersBy(String username, int pageNo, int pageSize) {
        return userRepository.findByUsernameContainingIgnoreCase(username, PageRequest.of(pageNo, pageSize))
                .stream()
                .map(user -> modelMapper.map(user, UserGetDto.class))
                .toList();
    }

    @Override
    @Transactional
    public UserGetDto updateUser(UserUpdateDto userUpdateDto, String userIdentifier) {
        User user = userRepository.findByUsernameOrEmail(userIdentifier, userIdentifier)
                .orElseThrow(() -> new EntityNotFoundException(USER_NOT_FOUND));

        modelMapper.map(userUpdateDto, user);

        return modelMapper.map(user, UserGetDto.class);
    }

    @Override
    @Transactional
    public void updatePassword(PasswordUpdateDto passwordUpdateDto, String userIdentifier) {
        User user = userRepository.findByUsernameOrEmail(userIdentifier, userIdentifier)
                .orElseThrow(() -> new EntityNotFoundException(USER_NOT_FOUND));

        checkPasswordMatch(passwordUpdateDto.getOldPassword(), user.getPassword());

        user.setPassword(passwordEncoder.encode(passwordUpdateDto.getNewPassword()));
    }

    @Override
    @Transactional
    public void deleteUser(String password, String userIdentifier) {
        User user = userRepository.findByUsernameOrEmail(userIdentifier, userIdentifier)
                .orElseThrow(() -> new EntityNotFoundException(USER_NOT_FOUND));

        checkPasswordMatch(password, user.getPassword());

        userRepository.deleteById(user.getId());
    }

    private void checkPasswordMatch(String clientPassword, String realPassword) {
        if (!passwordEncoder.matches(clientPassword, realPassword))
            throw new IllegalStateException(PASSWORD_MISMATCH);
    }
}
