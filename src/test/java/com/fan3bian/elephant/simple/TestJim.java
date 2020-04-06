package com.fan3bian.elephant.simple;

import com.fan3bian.elephant.ElephantApplicationTests;
import com.fan3bian.elephant.domain.entity.User;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.jd.fpl.cache.client.CacheClient;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import javax.annotation.Resource;
import java.util.ArrayList;

public class TestJim extends ElephantApplicationTests {

    @Resource
    private CacheClient cacheClient;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private RedisTemplate redisTemplate;

//    @Resource
//    private Cluster jimClient;

    @Test
    public void test() throws JsonProcessingException {
//        cacheClient.set("clps-shuyi","clps-quao");
//        System.out.println(cacheClient.get("clps-shuyi"));
//        Map<String, String> user = cacheClient.hGetAll("user");
//        System.out.println(JsonUtil.toJson(user));
//        if (cacheClient.exists("user")) {
//            cacheClient.del("user");
//        }
        User user = new User();
        user.setId(1L);
        user.setPassword("2");
        ArrayList<User> users = Lists.newArrayList(user);

        byte[] value = new ObjectMapper().writeValueAsBytes(users);
        String msg = new ObjectMapper().writeValueAsString(users);

        byte[] key = new ObjectMapper().writeValueAsBytes("clps-qa");
//        cacheClient.set(key,value);

        redisTemplate.opsForValue().set(1, msg);
    }

    @Test
    public void testSerializer() {
        User user = new User();
        user.setId(1L);
        user.setPassword("2");
        ArrayList<User> users = Lists.newArrayList(user);

//        stringRedisTemplate.setKeySerializer(new StringRedisSerializer());
//        stringRedisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
//        stringRedisTemplate.opsForValue().set("1",users);

        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplate.opsForValue().set("1", users);

    }

    @Test
    public void testJackSon() throws JsonProcessingException {
        User user = new User();
        user.setId(1L);
        user.setPassword("2");
        ArrayList<User> users = Lists.newArrayList(user);

        String s = new ObjectMapper().writeValueAsString(users);
        byte[] value = new ObjectMapper().writeValueAsBytes(users);
        redisTemplate.opsForValue().set(2, value);
//        System.out.println();


    }

    @Test
    public void testJedis() throws JsonProcessingException {
//        Jedis jedis = new Jedis("47.105.38.61", 6379);  //指定Redis服务Host和port
        User user = new User();
        user.setId(1L);
        user.setPassword("2");
        ArrayList<User> users = Lists.newArrayList(user);
//        Byte.valueOf("3")
//        byte[] value = objectMapper.writeValueAsBytes(users);
//        jedis.set()
//        jedis.set("quao",s);
        GenericJackson2JsonRedisSerializer genericJackson2JsonRedisSerializer = new GenericJackson2JsonRedisSerializer();
//        byte[] serializeKey = genericJackson2JsonRedisSerializer.serialize(1);
        byte[] serializeValue = genericJackson2JsonRedisSerializer.serialize(users);

        String str = new String(serializeValue);
        System.out.println(str);

//        jedis.set(serializeKey,serializeValue);


        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new SimpleModule().addSerializer(new NullValueSerializer(null)));
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY);

        byte[] value = objectMapper.writeValueAsBytes(users);
        String str2 = new String(value);
        System.out.println(str2);
//        objectMapper.registerModule(new SimpleModule().addSerializer(new NullValueSerializer(null)));
//        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY);
//
//        byte[] key = objectMapper.writeValueAsBytes(2);
//        byte[] value = objectMapper.writeValueAsBytes(users);
//
//
//        jedis.set(key,value);
//        jedis.close(); //使用完关闭连接

    }
}
