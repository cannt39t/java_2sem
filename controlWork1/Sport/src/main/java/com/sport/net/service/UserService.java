package com.sport.net.service;

import com.sport.net.dto.UserDto;
import com.sport.net.model.User;

import java.util.List;

public interface UserService {

    List<UserDto> getAll();
    UserDto get(int id);
    void register(User user);

    boolean login (User user);
}