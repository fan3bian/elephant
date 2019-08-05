package com.fan3bian.elephant.dao;

import com.fan3bian.elephant.domain.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserDao {

    int insert(User user);

    @Select("SELECT * FROM t_user WHERE username = #{username}")
    List<User> queryUsersByName(String userName);
}
