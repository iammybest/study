package com.iammybest.constants;

import com.iammybest.ehcache.CacheFactory;
import com.iammybest.ehcache.CacheProvider;
import com.iammybest.ehcache.UserEntity;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Hello World!");

        CacheProvider provider = CacheFactory.getEhCacheProvider("Deng", Long.class, UserEntity.class);

        provider.put(111L, new UserEntity(111L, "MrDeng"));

        for (int i = 0; i < 10; i++) {
            System.out.println(provider.get(111L));

            Thread.sleep(3000L);
        }

    }
}
