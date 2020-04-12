package com.iammybest.ehcache.impl;

import com.iammybest.ehcache.CacheProvider;
import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.CacheConfiguration;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.expiry.Duration;
import org.ehcache.expiry.Expirations;

import java.util.concurrent.TimeUnit;

/**
 * Created by MrDeng on 2017/2/22.
 */
public class EhcacheProviderImpl implements CacheProvider {
    Cache ehcache;

    public EhcacheProviderImpl(String cacheMark, Class<?> keyClazz, Class<?> valueClazz) {
        init(cacheMark, keyClazz, valueClazz);
    }

    private void init(String cacheMark, Class<?> keyClazz, Class<?> valueClazz) {

        CacheConfiguration config = CacheConfigurationBuilder.newCacheConfigurationBuilder(keyClazz, valueClazz, ResourcePoolsBuilder.heap(10000))
                .withExpiry(Expirations.timeToLiveExpiration(new Duration(5, TimeUnit.SECONDS)))
                .withExpiry(Expirations.timeToIdleExpiration(new Duration(2, TimeUnit.SECONDS)))
                .build();
        CacheManager cacheManager = CacheManagerBuilder.newCacheManagerBuilder().withCache(cacheMark, config).build(true);
        if (cacheManager.getCache(cacheMark, keyClazz, valueClazz) == null) {
            ehcache = cacheManager.createCache(cacheMark,config);
        } else {
            ehcache = cacheManager.getCache(cacheMark, keyClazz, valueClazz);
        }
    }

    @Override
    public void put(Object key, Object value) {
        ehcache.put(key, value);
    }

    @Override
    public Object get(Object key) {
        return ehcache.get(key);
    }

    @Override
    public void remove(Object key) {
        ehcache.remove(key);
    }
}
