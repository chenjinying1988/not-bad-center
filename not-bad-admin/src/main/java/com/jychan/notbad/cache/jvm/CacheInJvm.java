package com.jychan.notbad.cache.jvm;

/**
 * Created by chenjinying on 2017/6/24.
 * mail: 415683089@qq.com
 */
public interface CacheInJvm {

    public void put(String key, Object object);
    public Object get(String key);
}
