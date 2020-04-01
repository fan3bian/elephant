package com.fan3bian.elephant.service;

import com.fan3bian.elephant.ElephantApplicationTests;
import com.fan3bian.elephant.domain.Result;
import com.fan3bian.elephant.domain.entity.User;
import org.junit.Assert;
import org.junit.Test;

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
        User user = new User();
        user.setUsername("Picasso");
        user.setPassword("quao");
        List<User> users = userService.queryUsers();
        System.out.println(users);
//        Assert.assertEquals(1,);
//        Result<String> result = helloService.echo("quao");
//        System.out.println(result.getMsg());
//        List<User> users = userService.queryUsers();
//        System.out.println(users);
    }
    @Test
    public void getUser() {
        User user = new User();
        user.setId(2L);
        user.setUsername("Picasso");
        user.setPassword("quao");
        User user1 = userService.getUser(user);
        System.out.println(user1);
//        Assert.assertEquals(1,);
//        Result<String> result = helloService.echo("quao");
//        System.out.println(result.getMsg());
//        List<User> users = userService.queryUsers();
//        System.out.println(users);
    }
}
