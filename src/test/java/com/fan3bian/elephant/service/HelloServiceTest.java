package com.fan3bian.elephant.service;

import com.fan3bian.elephant.ElephantApplicationTests;
import com.fan3bian.elephant.domain.Result;
import com.fan3bian.elephant.domain.entity.User;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

public class HelloServiceTest extends ElephantApplicationTests {
    @Resource
    private HelloService helloService;

    @Resource
    private UserService userService;

    @Test
    public void testHello() {
        Result<String> result = helloService.echo("quao");
        System.out.println(result.getMsg());
        List<User> users = userService.queryUsers(null);
        System.out.println(users);
    }
}
