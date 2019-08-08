package com.fan3bian.elephant.dao;

import com.fan3bian.elephant.domain.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.BaseMapper;

import java.util.List;

@Mapper
public interface UserDao extends BaseMapper<User> {

    int insert(User user);

    @Select("SELECT * FROM t_user WHERE username = #{username}")
    List<User> queryUsersByName(String userName);

    int countByUsername(String username);

    @Select("SELECT * FROM t_user WHERE id = #{id}")
    User getUser(Long id);
}
