//package com.fan3bian.elephant.cache;
//
//import org.springframework.cache.interceptor.AbstractCacheResolver;
//import org.springframework.cache.interceptor.CacheOperationInvocationContext;
//import org.springframework.cache.interceptor.SimpleCacheResolver;
//
//import java.util.Collection;
//
//public class FplCacheResolver extends AbstractCacheResolver {
//
//    @Override
//    protected Collection<String> getCacheNames(CacheOperationInvocationContext<?> context) {
//        return (cacheManager != null ? new SimpleCacheResolver(cacheManager) : null);
//    }
//}
