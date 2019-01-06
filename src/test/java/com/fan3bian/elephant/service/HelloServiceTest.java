package com.fan3bian.elephant.service;

import com.fan3bian.elephant.ElephantApplicationTests;
import com.fan3bian.elephant.domain.Result;
import org.junit.Test;

import javax.annotation.Resource;

public class HelloServiceTest extends ElephantApplicationTests {
    @Resource
    private HelloService helloService;
    @Test
    public void testHello(){
        Result<String> result = helloService.echo("quao");
        System.out.println(result.getMsg());
    }
}
