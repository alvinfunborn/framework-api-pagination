package com.alvin.framework.api.pagination.paginator;

import com.alvin.framework.api.pagination.Result.Result;
import com.alvin.framework.api.pagination.method.ListMethodHandle;
import com.alvin.framework.api.pagination.param.StreamPageParam;
import com.alvin.framework.api.pagination.param.TablePageParam;

/**
 * datetime 2019/5/22 11:27
 *
 * @author sin5
 */
public interface CacheBasedPaginator {

    /**
     * paginate list by page+size
     *
     * @param lister lister invoke to get list
     * @param param page param
     * @param cacheKey cache key
     * @return Result
     */
    <T> Result<T> paginate(ListMethodHandle<T> lister, TablePageParam param, String cacheKey);

    /**
     * paginate list by cursor+size
     *
     * @param lister lister invoke to get list
     * @param param page param
     * @param cacheKey cache key
     * @return Result
     */
    <T> Result<T> paginate(ListMethodHandle<T> lister, StreamPageParam param, String cacheKey);
}
