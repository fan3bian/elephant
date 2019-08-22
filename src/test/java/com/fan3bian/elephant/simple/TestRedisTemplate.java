package com.fan3bian.elephant.simple;

import com.fan3bian.elephant.ElephantApplicationTests;
import org.junit.Test;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;


public class TestRedisTemplate extends ElephantApplicationTests {
    @Resource
    StringRedisTemplate stringRedisTemplate;

    @Test
    public void test() {
//        stringRedisTemplate.opsForValue().set("k1", "v1");

//        String v1 = stringRedisTemplate.opsForValue().get("k1");

        Boolean bool = stringRedisTemplate.opsForValue().setIfAbsent("", "");


        System.out.println(bool);
    }
}
