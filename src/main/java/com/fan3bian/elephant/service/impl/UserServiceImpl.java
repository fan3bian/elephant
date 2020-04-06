package com.fan3bian.elephant.service.impl;

import com.fan3bian.elephant.annotation.RepeatSubmit;
import com.fan3bian.elephant.domain.entity.User;
import com.fan3bian.elephant.service.UserService;
import com.fan3bian.elephant.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
@Slf4j
//@CacheConfig(cacheNames = "user")
public class UserServiceImpl implements UserService {
    @Resource
    private JdbcTemplate jdbcTemplate;

    @Override
    @RepeatSubmit(bizNo ="#user.username")
    public int addUser(User user) {
        String sql = "insert into t_user(username, password) values(?, ?)";
        return jdbcTemplate.update(sql, user.getUsername(), user.getPassword());
    }

    @Override
    @RepeatSubmit("asdf")
    public void delUser(User user) {
    }

    @Override
    @Cacheable(value = "user",key ="#user.id")
    public User getUser(User user) {
        System.out.println("没有查缓存哦");
        String sql = "select * from t_user where id = ?";
        return jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<>(User.class),user.getId());

    }
    @Override
    @Cacheable(value = "users",key ="#user.id" )
    public List<User> queryUsers(User user){
        String sql = "select * from t_user";
        log.info("没有查缓存哦");
        return jdbcTemplate.query(sql, new Object[]{}, new BeanPropertyRowMapper<>(User.class));
//        log.info(JsonUtil.toJson(query));
//        return query;
    }
}
