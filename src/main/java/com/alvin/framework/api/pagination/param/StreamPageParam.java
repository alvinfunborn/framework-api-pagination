package com.alvin.framework.api.pagination.param;

import com.alvin.framework.api.pagination.method.CommonMethodHandle;

/**
 * datetime 2019/5/22 10:39
 *
 * @author sin5
 */
public class StreamPageParam extends PageParam {

    /**
     * cursor
     */
    private String lastId;
    /**
     * idExtractor to extract id from item
     */
    private CommonMethodHandle<String> idExtractor;

    public StreamPageParam(int size, String lastId, CommonMethodHandle<String> idExtractor) {
        super(size);
        this.lastId = lastId;
        this.idExtractor = idExtractor;
    }

    public StreamPageParam(int size, Long timestamp, String lastId, CommonMethodHandle<String> idExtractor) {
        super(size, timestamp);
        this.lastId = lastId;
        this.idExtractor = idExtractor;
    }

    public StreamPageParam(int size, CommonMethodHandle<String> idExtractor) {
        super(size);
        this.idExtractor = idExtractor;
    }

    public String getLastId() {
        return lastId;
    }

    public CommonMethodHandle<String> getIdExtractor() {
        return idExtractor;
    }
}
