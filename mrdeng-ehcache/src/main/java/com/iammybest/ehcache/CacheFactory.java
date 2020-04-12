package com.iammybest.ehcache;


import com.iammybest.ehcache.impl.EhcacheProviderImpl;

/**
 * Created by MrDeng on 2017/2/22.
 * 缓存工厂方法
 */
public final class CacheFactory {
    /**
     * 获取Ecache缓存
     *
     * @return
     */
    public static CacheProvider getEhCacheProvider(String cacheMark, Class<?> keyClazz, Class<?> valueClazz) {
        return new EhcacheProviderImpl(cacheMark,keyClazz,valueClazz);
    }
}
