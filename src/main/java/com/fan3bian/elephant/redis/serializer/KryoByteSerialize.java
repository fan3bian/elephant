package com.fan3bian.elephant.redis.serializer;

/**
 * kryo序列化和反序列化实现类
 * DATE:2017/8/17
 */
public class KryoByteSerialize implements RedisSerializer {

    @Override
    public <T> byte[] serialize(T byteArray) {
        return KryoUtil.writeToByteArray(byteArray);
    }

    @Override
    public <T> T deserialize(Object obj, Class<?> clazz) {
        if (obj == null || ! (obj instanceof byte[])) {
           throw new IllegalArgumentException("KryoByteSerializeImpl类型错误，不是byte[]");
        }
        return KryoUtil.readFromByteArray((byte[]) obj);
    }
}
