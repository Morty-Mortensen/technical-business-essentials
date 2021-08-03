package com.essentials.business.technical.utils;

import java.util.HashMap;
import java.util.Map;

public class Cache {
    private static Cache cache;
    private Map<String, Object> internalCache = new HashMap<>();
    public static final String USER_AGENT = "User-Agent";
    public static final String APPLICATION_TYPE = "Content-Type";

    private Cache() {
    }

    public static synchronized Cache getInstance() {
        if (cache == null) {
            cache = new Cache();
        }
        return cache;
    }

    public void set(String key, Object value) {
        this.internalCache.put(key, value);
    }

    public Object get(String key) {
        return this.internalCache.get(key);
    }

    public void clear() {
        this.internalCache.clear();
    }
}
