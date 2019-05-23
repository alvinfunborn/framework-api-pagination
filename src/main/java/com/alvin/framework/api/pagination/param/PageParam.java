package com.alvin.framework.api.pagination.param;

/**
 * datetime 2019/5/22 10:38
 *
 * @author sin5
 */
public class PageParam {

    /**
     * pageSize
     */
    private int size;

    /**
     * paginate timestamp
     */
    private Long timestamp;

    public PageParam(int size) {
        this.size = size;
    }

    public PageParam(int size, Long timestamp) {
        this.size = size;
        this.timestamp = timestamp;
    }

    public int getSize() {
        return size;
    }

    public Long getTimestamp() {
        return timestamp;
    }
}
