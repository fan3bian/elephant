package com.fan3bian.elephant.manger;


import com.fan3bian.elephant.ElephantApplicationTests;
import com.fan3bian.elephant.domain.entity.User;
import com.fan3bian.elephant.manager.UserManager;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

public class UserManagerTest extends ElephantApplicationTests {
    @Resource
    private UserManager userManager;

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
}
