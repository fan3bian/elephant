package com.fan3bian.elephant.manger;


import com.fan3bian.elephant.ElephantApplicationTests;
import com.fan3bian.elephant.dao.UserDao;
import com.fan3bian.elephant.domain.entity.User;
import com.fan3bian.elephant.manager.UserManager;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

public class UserManagerTest extends ElephantApplicationTests {
    @Resource
    private UserManager userManager;
    @Resource
    private UserDao userDao;
    @Test
    public void testAddUser(){
        User user = new User();
        user.setUsername("Picasso");
        user.setPassword("quao");
        Assert.assertEquals(1,userManager.addUser(user));
    }
    @Test
    public void queryUsers(){
        List<User> picasso = userManager.queryUsersByName("fan3bian");
        System.out.println(picasso);
    }
    @Test
    public void testQueryAll(){
        final PageInfo<Object> pageInfo = PageHelper.startPage(1, 10).setOrderBy("id desc").doSelectPageInfo(() -> this.userDao.selectAll());
        System.out.println(pageInfo);
    }
}
