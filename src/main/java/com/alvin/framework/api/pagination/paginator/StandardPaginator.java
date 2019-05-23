package com.alvin.framework.api.pagination.paginator;

import com.alvin.framework.api.pagination.Result.Result;
import com.alvin.framework.api.pagination.param.StreamPageParam;
import com.alvin.framework.api.pagination.param.TablePageParam;

import java.util.List;

/**
 * datetime 2019/5/22 11:16
 *
 * @author sin5
 */
public class StandardPaginator implements Paginator {

    @Override
    public <T> Result<T> paginate(List<T> list, TablePageParam param) {
        return new Result<>(list, param.getPage(), param.getSize(), param.getTimestamp() == null ? null : String.valueOf(param.getTimestamp()));
    }

    @Override
    public <T> Result<T> paginate(List<T> list, StreamPageParam param) {
        return new Result<>(list, param.getIdExtractor(), param.getLastId(), param.getSize(), param.getTimestamp() == null ? null : String.valueOf(param.getTimestamp()));
    }
}
