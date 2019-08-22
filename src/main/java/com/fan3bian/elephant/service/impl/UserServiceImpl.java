package com.fan3bian.elephant.service.impl;

import com.fan3bian.elephant.annotation.RepeatSubmit;
import com.fan3bian.elephant.domain.entity.User;
import com.fan3bian.elephant.service.UserService;
import com.fan3bian.elephant.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
@Slf4j
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
    @RepeatSubmit(bizNo ="#user.username")
    public void delUser(User user) {
    }

    @Override
    public int getUser(User user) {
        return 0;
    }
    @Override
    public List<User> queryUsers(){
        String sql = "select * from t_user";
        return jdbcTemplate.query(sql, new Object[]{}, new BeanPropertyRowMapper<>(User.class));
//        log.info(JsonUtil.toJson(query));
//        return query;
    }
}
