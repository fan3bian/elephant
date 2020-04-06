package com.fan3bian.elephant.service;

import com.fan3bian.elephant.domain.entity.User;

import java.util.List;

public interface UserService {
    int addUser(User user);

    void delUser(User user);

    User getUser(User user);

    List<User> queryUsers(User user);
}
