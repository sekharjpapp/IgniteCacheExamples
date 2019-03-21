package com.pixelignite.cache;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.IgniteException;
import org.apache.ignite.Ignition;


public class CacheApiExample {
    public static void main(String[] args) throws IgniteException {
        Ignition.setClientMode(true);

        try(Ignite ignite = Ignition.start("F:\\apache-ignite\\apache-ignite-2.7.0-bin\\examples\\config\\example-ignite.xml")) {
             IgniteCache<Integer,String> cache = ignite.getOrCreateCache("myCache");
             cache.put(1,"Hello");
             cache.put(2,"World");
             ignite.compute().broadcast(()->System.out.println(cache.get(1) + "  " + cache.get(2)));
        }
    }
}
