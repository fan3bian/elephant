package com.fan3bian.elephant.controller;

import com.fan3bian.elephant.domain.Knight;
import com.fan3bian.elephant.domain.Result;
import com.fan3bian.elephant.service.HelloService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class HelloWorldController {

    @Resource
    private Knight knight;
    @Resource
    private HelloService helloService;

    @RequestMapping("/hello")
    public String index() {
        return "Hello World";
    }

    @RequestMapping("/echo")
    public String echo(String str) {
        Result<String> echo = helloService.echo(str);
        return echo.getMsg();
    }
    @GetMapping("/getKnight")
    public Knight register(){
        log.info(knight.toString());
        return knight;
    }
    @PostMapping("/callKnight")
    public Knight call(){
        log.info(knight.toString());
        return knight;
    }
}