package com.fan3bian.elephant.manager.impl;

import com.fan3bian.elephant.dao.UserDao;
import com.fan3bian.elephant.domain.entity.User;
import com.fan3bian.elephant.manager.UserManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class UserManagerImpl implements UserManager {

    @Resource
    private UserDao userDao;

    @Override
    public int addUser(User user) {
        return userDao.insert(user);
    }

    @Override
    public List<User> queryUsersByName(String userName) {
        return userDao.queryUsersByName(userName);

    }

    @Override
//    @Cacheable(value = "user", key = "#id",cacheResolver = "")
    public User getUser(Long id) {
        return userDao.getUser(id);
    }
}
