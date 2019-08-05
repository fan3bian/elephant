package com.fan3bian.elephant.service;

import com.fan3bian.elephant.domain.entity.User;

import java.util.List;

public interface UserService {
    int addUser(User user);

    int delUser(User user);

    int getUser(User user);

    List<User> queryUsers();
}
