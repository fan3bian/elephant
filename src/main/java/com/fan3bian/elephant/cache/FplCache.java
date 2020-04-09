package com.fan3bian.elephant.cache;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.jd.fpl.cache.client.CacheClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.Cache;
import org.springframework.cache.support.AbstractValueAdaptingCache;
import org.springframework.cache.support.SimpleValueWrapper;

import java.util.concurrent.Callable;

/**
 * @author zhangshuyi1
 */
@Slf4j
public class FplCache extends AbstractValueAdaptingCache implements Cache {

    /**
     * cacheName, also use as cache key
     */
    private String key;
    /**
     * 除id之外的其他缓存field，清空缓存时用来关联清空
     */
    private String[][] evictRelFields;

    private CacheClient cacheClient;

    public FplCache(String key, CacheClient cacheClient) {
        super(false);
        this.cacheClient = cacheClient;
        this.key = key;
    }

    final ObjectMapper objectMapper = new ObjectMapper();

    {
        objectMapper.registerModule(new SimpleModule().addSerializer(new NullValueSerializer(null)));
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY);
    }

    @Override
    public String getName() {
        return key;
    }

    @Override
    public Object getNativeCache() {
        return cacheClient;
    }

    @Override
    public ValueWrapper get(Object field) {
        try {
            //1.field会不会为空
            final String fieldStr = field.toString();
            //是使用hset还是使用set
            String s = cacheClient.hGet(key, fieldStr);
            //当value==null时，需要返回null
            if (s != null) {
                Object value = objectMapper.readValue(s, Object.class);
                return value == null ? null : new SimpleValueWrapper(value);
            }
        } catch (Exception e) {
            log.error("读取缓存异常", e);
        }
        return null;
    }

    @Override
    public <T> T get(Object key, Class<T> type) {
        return null;
    }

    @Override
    protected Object lookup(Object key) {
        System.out.println("lookup");
        return null;
    }

    @Override
    public <T> T get(Object key, Callable<T> valueLoader) {
        return null;
    }

    @Override
    public void put(Object field, Object value) {
        try {

            final String fieldStr = field.toString();
            String s = objectMapper.writeValueAsString(value);
            cacheClient.hSet(key, field.toString(), s);

        } catch (Exception e) {
            log.error("设置缓存异常", e);
        }
    }

    @Override
    public ValueWrapper putIfAbsent(Object key, Object value) {
        return null;
    }

    @Override
    public void evict(Object field) {
        if (field == null || StringUtils.isBlank(field.toString())) {
            return;
        }
        final StringBuilder sb = new StringBuilder(500);
        try {
            String fieldOri = field.toString().replaceAll("\\[", "").replaceAll("\\]", "");
            final String fieldStr = fieldOri;
            String[] arrKey = fieldStr.split("[,]");
            for (int idx = 0; idx < arrKey.length; idx++) {
                String fieldNow = arrKey[idx].trim();
                cacheClient.hDel(key, fieldNow);
                log.info("redis执行器 void evict(Object key)：keyStr:---->" + key + "；field--->" + fieldNow);
            }
        } catch (Exception e) {
            log.error("com.jd.clps.master.redis.exception#清除缓存异常", e);
            sb.append(field.toString());
        }
    }


    @Override
    public void clear() {
        log.info("redis执行器  void clear()：field:---->" + key);
    }
}

