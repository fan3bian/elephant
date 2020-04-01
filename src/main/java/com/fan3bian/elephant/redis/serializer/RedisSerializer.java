package com.fan3bian.elephant.redis.serializer;

/**
 * 序列化接口
 * USER:zhengguinan
 * DATE:2017/3/3
 */
public interface RedisSerializer {
    /**
     * 序列化对象
     *
     * @param t 泛型实体
     * @return 返回序列化后的字符串
     */
    <T>  Object serialize(T t);

    /**
     * 反序列化对象
     *
     * @param obj   反序列化的对象
     * @param clazz 反序列化的class对象
     * @return 返回反序列化后的实体
     */
    <T> T deserialize(Object obj, Class<?> clazz);
}
