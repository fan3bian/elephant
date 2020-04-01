package com.fan3bian.elephant.redis;

import com.jd.jim.cli.Cluster;
import org.apache.log4j.Logger;

/**
 * Redis执行器
 * User: clps
 * Date: 15-2-6
 * Time: 下午3:56
 */
public abstract class RedisExecutor<T> {
    /** redis客户端 */
    protected Cluster jimClient;
    private static final Logger logger = Logger.getLogger(RedisExecutor.class);
    /**
     * 构造函数
     * @param redisClient
     */
    public RedisExecutor(Cluster redisClient) {
        this.jimClient = redisClient;
    }

    /**
     * 回调
     * @return 执行结果
     */
    abstract T execute();

    /**
     * 调用{@link #execute()}并返回执行结果
     * @return 执行结果
     */
    public T getResult() {
        T result = null;
        try {
            result = execute();
        } catch (Throwable e) {
            logger.error("Redis execute exception",e);
//            throw new RuntimeException("Redis execute exception", e);
        } finally {
        }
        return result;
    }
}
