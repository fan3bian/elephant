package com.fan3bian.elephant.cache;

import com.jd.fpl.cache.client.CacheClient;
import org.springframework.cache.Cache;
import org.springframework.cache.support.AbstractCacheManager;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * 缓存管理器
 * User: clps
 * Date: 15-2-6
 * Time: 下午3:56
 */
@Service
public class FplCacheManager extends AbstractCacheManager {

    @Resource
    private CacheClient cacheClient;

    private Collection<? extends Cache> caches;


    public void setCaches(List<Cache> caches) {
        this.caches = caches;
    }

    @Override
    protected Collection<? extends Cache> loadCaches() {
        if (caches == null) {
            return Collections.emptySet();
        }
        return caches;
    }

    @Override
    protected Cache getMissingCache(String name) {
        FplCache cache = new FplCache(name, cacheClient);
        return cache;
    }
}
