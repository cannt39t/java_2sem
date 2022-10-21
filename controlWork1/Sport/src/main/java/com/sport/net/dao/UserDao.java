package com.sport.net.dao;

import com.sport.net.model.User;

import java.util.List;

public interface UserDao {

    User get(int id);

    List<User> getAll();

    void save(User user);

    boolean login(User user);

}
