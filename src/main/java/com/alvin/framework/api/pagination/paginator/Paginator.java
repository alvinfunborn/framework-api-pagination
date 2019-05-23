package com.alvin.framework.api.pagination.paginator;

import com.alvin.framework.api.pagination.Result.Result;
import com.alvin.framework.api.pagination.param.StreamPageParam;
import com.alvin.framework.api.pagination.param.TablePageParam;

import java.util.List;

/**
 * datetime 2019/5/22 10:43
 *
 * @author sin5
 */
public interface Paginator {

    /**
     * paginate list by page+size
     *
     * @param list list
     * @param param page param
     * @return Result
     */
    <T> Result<T> paginate(List<T> list, TablePageParam param);

    /**
     * paginate list by cursor+size
     *
     * @param list list
     * @param param page param
     * @return Result
     */
    <T> Result<T> paginate(List<T> list, StreamPageParam param);
}
