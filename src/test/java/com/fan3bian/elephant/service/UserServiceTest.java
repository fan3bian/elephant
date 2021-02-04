package com.fan3bian.elephant.service;

import com.fan3bian.elephant.ElephantApplicationTests;
import com.fan3bian.elephant.domain.Result;
import com.fan3bian.elephant.domain.entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.Resource;
import java.util.List;

public class UserServiceTest extends ElephantApplicationTests {
    @Resource
    private UserService userService;

    @Test
    public void testHello() {

        User user = new User();
        user.setUsername("Picasso");
        user.setPassword("quao");
        int i = userService.addUser(user);
        System.out.println(i);
//        Assert.assertEquals(1,);
//        Result<String> result = helloService.echo("quao");
//        System.out.println(result.getMsg());
//        List<User> users = userService.queryUsers();
//        System.out.println(users);
    }

    @Test
    public void delUser() {
        User user = new User();
        user.setUsername("Picasso");
        user.setPassword("quao");
        userService.delUser(user);
//        Assert.assertEquals(1,);
//        Result<String> result = helloService.echo("quao");
//        System.out.println(result.getMsg());
//        List<User> users = userService.queryUsers();
//        System.out.println(users);
    }
    @Test
    public void queryUser() {

        List<User> users = userService.queryUsers(new User());
        System.out.println(users.getClass());
//        Assert.assertEquals(1,);
//        Result<String> result = helloService.echo("quao");
//        System.out.println(result.getMsg());
//        List<User> users = userService.queryUsers();
//        System.out.println(users);
    }
}