package com.alvin.framework.api.pagination.service;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * datetime 2019/5/23 11:50
 *
 * @author sin5
 */
public interface ListCache {

    /**
     * get list from cache
     *
     * @param key cache key
     * @param timestamp timestamp of list
     * @return list
     */
    <T> List<T> get(String key, String timestamp);

    /**
     * cache list
     *
     * @param key cache key
     * @param timestamp timestamp of list
     * @param list list
     * @param expire expire time
     * @param timeUnit timeunit
     */
    <T> void cache(String key, String timestamp, List<T> list, long expire, TimeUnit timeUnit);
}
