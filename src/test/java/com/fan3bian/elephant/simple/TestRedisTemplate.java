//package com.fan3bian.elephant.simple;
//
//import com.fan3bian.elephant.ElephantApplicationTests;
//import org.junit.Test;
//import org.springframework.data.redis.core.StringRedisTemplate;
//
//import javax.annotation.Resource;
//import java.util.Comparator;
//import java.util.Set;
//
//
//public class TestRedisTemplate extends ElephantApplicationTests {
//    @Resource
//    StringRedisTemplate stringRedisTemplate;
//
//    @Test
//    public void test() {
//
//        String v1 = stringRedisTemplate.opsForValue().get("k1");
//        System.out.println(v1);
//        Boolean bool = stringRedisTemplate.opsForValue().setIfAbsent("k1", "k1");
//        System.out.println(bool);
//    }
//    @Test
//    public void testKeys(){
//        Set<String> keys = stringRedisTemplate.keys("*");
//        System.out.println(keys);
//        keys.forEach(x -> System.out.println(stringRedisTemplate.opsForValue().get(x)));
//
//        Comparator<String> comparator = new Comparator<String>() {
//            @Override
//            public int compare(String o1, String o2) {
//                return 1;
//            }
//        };
//        int compare = comparator.compare("", "");
//
//
//    }
//}
