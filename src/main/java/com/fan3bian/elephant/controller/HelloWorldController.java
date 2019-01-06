package com.fan3bian.elephant.controller;

import com.fan3bian.elephant.domain.Result;
import com.fan3bian.elephant.service.HelloService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class HelloWorldController {
    @Resource
    HelloService helloService;

    @RequestMapping("/hello")
    public String index() {
        return "Hello World";
    }

    @RequestMapping("/echo")
    public String echo(String str) {
        Result<String> echo = helloService.echo(str);
        return echo.getMsg();
    }
}