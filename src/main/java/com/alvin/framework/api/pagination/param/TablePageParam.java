package com.alvin.framework.api.pagination.param;

/**
 * datetime 2019/5/22 10:39
 *
 * @author sin5
 */
public class TablePageParam extends PageParam {

    /**
     * page: start with 1
     */
    private int page;

    public TablePageParam(int size, int page) {
        super(size);
        this.page = page;
    }

    public TablePageParam(int size, Long timestamp, int page) {
        super(size, timestamp);
        this.page = page;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
