package com.fan3bian.elephant.redis;

import org.springframework.cache.Cache;
import org.springframework.cache.transaction.AbstractTransactionSupportingCacheManager;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * 缓存管理器
 * User: clps
 * Date: 15-2-6
 * Time: 下午3:56
 */
public class RedisCacheManager extends AbstractTransactionSupportingCacheManager {

    private Collection<? extends Cache> prefinedCaches;


    public void setCaches(List<Cache> caches) {
        this.prefinedCaches = caches;
    }

    @Override
    protected Collection<? extends Cache> loadCaches() {
        if (prefinedCaches == null) {
            return Collections.emptyList();
        }
        return prefinedCaches;
    }

}
