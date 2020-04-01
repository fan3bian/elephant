package com.fan3bian.elephant.redis;

import com.fan3bian.elephant.utils.JsonUtil;
import com.jd.jim.cli.Cluster;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.cache.Cache;
import org.springframework.core.task.TaskExecutor;

import java.util.concurrent.Callable;

import static java.lang.String.format;

/**
 * 订单redis-Hashes类型缓存实现类
 * User: clps
 * Date: 15-2-6
 * Time: 下午3:56
 */
public class RedisCache<T> implements Cache {
    /**
     * Logger for this class
     */
    private static final Logger logger = Logger.getLogger(RedisCache.class);

    /**
     * key下的field
     */
    private String key;
    private String clazz;
    /**
     * 除id之外的其他缓存field，清空缓存时用来关联清空
     */
    private String[][] evictRelFields;
    /**
     * 注入的redis客户端
     */
    private final Cluster client;

    private TaskExecutor taskExecutor;

    public RedisCache(String key, Cluster client, String clazz) {
        this.key = key;
        this.client = client;
        this.clazz = clazz;
    }

    @Override
    public String getName() {
        return key;
    }

    @Override
    public Cluster getNativeCache() {
        return client;
    }

    @Override
    public ValueWrapper get(Object field) {
        MasterValueWrapper result = null;
        try {
            final String fieldStr = field.toString();
            Object t = new RedisExecutor<Object>(client) {
                @Override
                Object execute() {
                    logger.info("redis执行器 ValueWrapper get(Object key)：keyStr:---->" + key + "; field--->" + fieldStr);
                    return client.hGet(key, fieldStr);
                }
            }.getResult();

            if (t != null) {
                result = new MasterValueWrapper(t, clazz);
            }
        } catch (Exception e) {
            logger.error("读取缓存异常", e);
        }

        return result;
    }

    @Override
    public <T> T get(Object key, Class<T> type) {
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
            final String valStr = JsonUtil.toJson(value);
            new RedisExecutor<Object>(client) {
                @Override
                Object execute() {
                    if (!"".equals(valStr)) {
                        logger.info("redis执行器 void put(Object key, Object value)：keyStr:---->" + key + "field--->" + fieldStr + "valStr--->" + valStr);
                        return client.hSet(key, fieldStr, valStr);
                    }
                    return true;
                }
            }.getResult();
        } catch (Exception e) {
            logger.error("设置缓存异常", e);
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
            new RedisExecutor<Object>(client) {
                @Override
                Object execute() {
                    String[] arrKey = fieldStr.split("[,]");
                    for (int idx = 0; idx < arrKey.length; idx++) {
                        String fieldNow = arrKey[idx].trim();
                        evictAllRelative(fieldNow, sb);
                        client.hDel(key, fieldNow);
                        logger.info("redis执行器 void evict(Object key)：keyStr:---->" + key + "；field--->" + fieldNow);
                    }
                    return arrKey.length;
                }
            }.getResult();
        } catch (Exception e) {
            logger.error("com.jd.clps.master.redis.exception#清除缓存异常", e);
            sb.append(field.toString());
        }

        if (StringUtils.isNotBlank(sb.toString())) {
            try {
                String reason = format("清除缓存异常#hDel(%s,%s)", key, sb.toString());
                logger.error("com.jd.clps.master.redis.exception#" + reason);
                sendMail("清除缓存异常，请手动清除", reason, "chenhaie@jd.com");
            } catch (Exception es) {
                logger.error("redis报警异常", es);
            }
        }
    }

    /**
     * 清除除了按id查询以外的其他缓存
     *
     * @param field
     * @param sb
     */
    protected void evictAllRelative(String field, StringBuilder sb) {
        if (evictRelFields == null || evictRelFields.length == 0) {
            return;
        }

        try {
            ValueWrapper valeMapper = get(field);
            if (valeMapper == null) {
                return;
            }
            Object delValue = valeMapper.get();
            if (delValue != null) {
                for (int i = 0; i < evictRelFields.length; i++) {
                    String delField = RedisFieldGenerator.generateField(delValue, evictRelFields[i]);
                    if (StringUtils.isNotBlank(delField) && delField.startsWith("delAsKey") && delField.contains(RedisFieldGenerator.REDIS_FIELD_SPLIT_CHAR)) {
                        String[] arrDelField = delField.substring(0, delField.length() - 1).split(RedisFieldGenerator.REDIS_FIELD_SPLIT_CHAR, 2);
                        client.del(arrDelField[1]);
                        logger.warn(format("RedisCache->evictAllRelative->del(%s)", arrDelField[1]));
                    } else if (StringUtils.isNotBlank(delField)) {
                        client.hDel(key, delField);
                        logger.info("redis执行器 void evict(Object key)：keyStr:---->" + key + "；field--->" + delField);
                    }
                }
            } else {
                String reason = "更新时清除联合field缓存异常，无数据：get(Object key)：key:---->" + key + "; field--->" + field;
                logger.error(reason);
                throw new RuntimeException(reason);
            }
        } catch (Exception e) {
            logger.error("缓存清除异常", e);
            sb.append(field).append(",");
        }
    }

    private void sendMail(String subject, String content, String receive) {
        if (taskExecutor != null) {
            taskExecutor.execute(new sendMailThread(subject, content, receive));
        }
    }

    class sendMailThread extends Thread {
        private String subject;
        private String content;
        private String receive;

        sendMailThread(String subject, String content, String receive) {
            this.subject = subject;
            this.content = content;
            this.receive = receive;
        }

        @Override
        public void run() {
            try {
            } catch (Exception e) {
                logger.error("发送邮件失败", e);
            }
        }
    }

    @Override
    public void clear() {
        logger.info("redis执行器  void clear()：field:---->" + key);
    }

    public void setEvictRelFields(String[][] evictRelFields) {
        this.evictRelFields = evictRelFields;
    }

    public void setTaskExecutor(TaskExecutor taskExecutor) {
        this.taskExecutor = taskExecutor;
    }
}

