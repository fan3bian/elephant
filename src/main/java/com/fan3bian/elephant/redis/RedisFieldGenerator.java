package com.fan3bian.elephant.redis;

import java.lang.reflect.Field;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

public class RedisFieldGenerator {
    private static final Logger logger = Logger.getLogger(RedisFieldGenerator.class);
    public static final String REDIS_FIELD_SPLIT_CHAR = "_";

	public static String generateField(Object delValue, String[] field) {
		if (field.length == 0) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
        try {
            for (String obj : field) {
                if (StringUtils.isNotBlank(obj)) {
                    String[] param = obj.split("=");
                    if (param.length == 2) {
                        sb = sb.append(param[1]).append(RedisFieldGenerator.REDIS_FIELD_SPLIT_CHAR);
                    } else {
                        Field fs = delValue.getClass().getDeclaredField(param[0]);
                        fs.setAccessible(true);
                        sb = sb.append(fs.get(delValue)).append(RedisFieldGenerator.REDIS_FIELD_SPLIT_CHAR);
                    }
                }
            }
        } catch (Exception e) {
            logger.error("清除缓存生成关联field时异常:", e);
            throw new RuntimeException("生成关联删除field异常", e);
        }

		return sb.toString();
	}
}
