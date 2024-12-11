package com.senla.api.service.imp;

import com.senla.api.dao.UserDao;
import com.senla.api.model.User;
import com.senla.api.service.UserService;
import com.senla.di.annotation.Autowired;
import com.senla.di.annotation.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class UserServiceImpl implements UserService {
    private UserDao userDao;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public Optional<User> createUser(User user) {
        return userDao.create(user);
    }

    @Override
    public Optional<User> getUserById(UUID id) {
        return userDao.getById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAll();
    }

    @Override
    public Optional<User> updateUser(User user, UUID id) {
        return userDao.update(user, id);
    }

    @Override
    public Optional<User> deleteUser(UUID id) {
        return Optional.empty();
    }
}
