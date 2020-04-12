package com.iammybest.ehcache;

/**
 * Created by MrDeng on 2017/2/22.
 */
public interface CacheProvider {
    static final long DEFAULT_TIME_OUT=7*24*60*60;
    public void put(Object key, Object value);
    public Object get(Object key);
    public void remove(Object key);
}
