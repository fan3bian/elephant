package com.fan3bian.elephant.manager;

import com.fan3bian.elephant.domain.entity.User;

import java.util.List;

public interface UserManager {

    int addUser(User user);

    List<User> queryUsersByName(String userName);

    User getUser(Long id);
}
