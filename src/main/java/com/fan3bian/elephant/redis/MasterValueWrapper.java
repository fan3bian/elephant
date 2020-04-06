package com.fan3bian.elephant.redis;

import com.fan3bian.elephant.utils.JsonUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.codehaus.jackson.type.JavaType;
import org.springframework.cache.Cache.ValueWrapper;

import java.io.IOException;
import java.util.List;

/**
 * Redis查询结果转换器
 * User: clps
 * Date: 15-2-6
 * Time: 下午3:56
 */
public class MasterValueWrapper implements ValueWrapper {
    /** Logger for this class */
    private static final Logger logger = Logger.getLogger(RedisCache.class);
    
    private Object value;


    /**
     * Create a new SimpleValueWrapper instance for exposing the given value.
     * @param value the value to expose (may be {@code null})
     * @param clazz 
     * @throws IOException 
     * @throws ClassNotFoundException 
     */
    public MasterValueWrapper(Object value, Object clazz) {
        try {
            if (value == null) {
                return;
            }
//            ObjectMapper objectMapper = new ObjectMapper();
            Object o = new ObjectMapper().readValue((String) value, Object.class);
            System.out.println(o.getClass());
            this.value = o;

//            String jsonStr = (String) value;
//            Class<?> clazz1 = Class.forName((String) clazz);
//            if (jsonStr.startsWith("[")) {
////                this.value = JsonUtil.fromJson(jsonStr, new TypeReference<List>(){});
//                JavaType javaType = JsonUtil.mapper().getTypeFactory().constructParametricType(List.class,clazz1);
//                this.value = JsonUtil.mapper().readValue(jsonStr,javaType);
//            } else {
//                this.value = JsonUtil.fromJson(jsonStr, clazz1);
//            }

        } catch (Exception e) {
            logger.error("缓存类型转换异常！", e);
            throw new RuntimeException("Erorr!!!!!!!!");
        }

    }


    /**
     * Simply returns the value as given at construction time.
     */
    public Object get() {
        return this.value;
    }

}
