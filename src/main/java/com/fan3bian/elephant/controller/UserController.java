package com.fan3bian.elephant.controller;

import com.fan3bian.elephant.domain.Constant;
import com.fan3bian.elephant.domain.Result;
import com.fan3bian.elephant.domain.entity.User;
import com.fan3bian.elephant.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/addUser")
    public Result addUser(@RequestBody User user) {
        int i = userService.addUser(user);
        return new Result(Boolean.TRUE, Constant.SUCCESS_MSG);
    }

    @GetMapping("/querUsers")
    public List<User> queryUsers() {
        return userService.queryUsers();
    }
}
