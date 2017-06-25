package com.jychan.notbad.cache.jvm;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by chenjinying on 2017/6/24.
 * mail: 415683089@qq.com
 */
public class CacheInJvmLinkedHashMap extends CacheInJvmHelper {

    private static CacheInJvmLinkedHashMap thisObj;

    /** 缓存容量 */
    private static int ARTIST_CACHE_MAX_SIZE = 5000;
    /** 缓存信息 */
    private static Map<String, Object> dataMap;

    private CacheInJvmLinkedHashMap(int maxSize) {
        dataMap = new LinkedHashMap<String, Object>() {
            @Override
            protected boolean removeEldestEntry(Map.Entry<String, Object> eldest) {
                return size() > maxSize;
            }
        };
    }

    public static CacheInJvm getDefault() {
        return getDefault(ARTIST_CACHE_MAX_SIZE);
    }

    public static CacheInJvm getDefault(int maxSize) {
        maxSize = maxSize > 0 ? maxSize : ARTIST_CACHE_MAX_SIZE;
        if (thisObj == null) {
            thisObj = new CacheInJvmLinkedHashMap(maxSize);
        }
        return thisObj;
    }



    // - - - - - - - - - - - - - - - -

    @Override
    public void put(String key, Object object) {
        dataMap.put(key, object);
    }

    @Override
    public Object get(String key) {
        return dataMap.get(key);
    }

    public static void main(String[] args) {
        CacheInJvmLinkedHashMap.getDefault().put("aa", "ddd");
        System.out.println(CacheInJvmLinkedHashMap.getDefault().get("aa"));
    }
}
