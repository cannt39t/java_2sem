package com.sport.net.service.impl;

import com.sport.net.dao.impl.UserDaoImpl;
import com.sport.net.dto.UserDto;
import com.sport.net.model.User;
import com.sport.net.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {

    UserDaoImpl userDao = new UserDaoImpl();

    @Override
    public List<UserDto> getAll() {
        return null;
    }

    @Override
    public UserDto get(int id) {
        return null;
    }

    @Override
    public void register(User user) {

        userDao.save(user);
    }
    @Override
    public boolean login(User user) {
        return userDao.login(user);
    }
}
