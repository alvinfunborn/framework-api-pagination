package com.alvin.framework.api.pagination.paginator;

import com.alvin.framework.api.pagination.Result.Result;
import com.alvin.framework.api.pagination.method.ListMethodHandle;
import com.alvin.framework.api.pagination.param.StreamPageParam;
import com.alvin.framework.api.pagination.param.TablePageParam;
import com.alvin.framework.api.pagination.service.ListCache;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * datetime 2019/5/22 11:35
 *
 * @author sin5
 */
@SuppressWarnings("Duplicates")
public class StandardCacheBasedPaginator implements CacheBasedPaginator {

    private long expire = 10 * 60 * 1000;
    private ListCache listCache;

    public StandardCacheBasedPaginator(ListCache listCache, long expire, TimeUnit timeUnit) {
        this.expire = timeUnit.toMillis(expire);
        this.listCache = listCache;
    }

    public StandardCacheBasedPaginator(ListCache listCache) {
        this.listCache = listCache;
    }

    @Override
    public <T> Result<T> paginate(ListMethodHandle<T> lister, TablePageParam param, String cacheKey) {
        Long timestamp = param.getTimestamp();
        if (timestamp == null) {
            timestamp = System.currentTimeMillis();
        }
        timestamp = timestamp / expire * expire;
        String timestampStr = String.valueOf(timestamp);
        List<T> list = listCache.get(cacheKey, timestampStr);
        if (list == null || list.isEmpty()) {
            list = lister.invoke();
            if (list == null) {
                list = Collections.emptyList();
            } else {
                listCache.cache(cacheKey, timestampStr, list, expire, TimeUnit.MILLISECONDS);
            }
        }
        return new Result<>(list, param.getPage(), param.getSize(), timestampStr);
    }

    @Override
    public <T> Result<T> paginate(ListMethodHandle<T> mh, StreamPageParam param, String cacheKey) {
        Long timestamp = param.getTimestamp();
        if (timestamp == null) {
            timestamp = System.currentTimeMillis();
        }
        timestamp = timestamp / expire * expire;
        String timestampStr = String.valueOf(timestamp);
        List<T> list = listCache.get(cacheKey, timestampStr);
        if (list == null || list.isEmpty()) {
            list = mh.invoke();
            if (list == null) {
                list = Collections.emptyList();
            } else {
                listCache.cache(cacheKey, timestampStr, list, expire, TimeUnit.MILLISECONDS);
            }
        }
        return new Result<>(list, param.getIdExtractor(), param.getLastId(), param.getSize(), timestampStr);
    }
}
