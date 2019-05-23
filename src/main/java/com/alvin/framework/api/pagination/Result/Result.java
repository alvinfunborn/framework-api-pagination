package com.alvin.framework.api.pagination.Result;

import com.alvin.framework.api.pagination.method.CommonMethodHandle;

import java.util.ArrayList;
import java.util.List;

/**
 * datetime 2019/5/22 10:44
 *
 * @author sin5
 */
public class Result<T> {

    /**
     * result list
     */
    private List<T> itemList;
    /**
     * total item count
     */
    private int totalItem;
    /**
     * total page count
     */
    private int totalPage;
    /**
     * paginate timestamp
     */
    private String timestamp;

    public Result(List<T> allItemList, int page, int size, String timestamp) {
        totalItem = allItemList.size();
        totalPage = totalItem % size == 0 ? totalItem / size : totalItem / size + 1;
        int startIndex = (page - 1) * size;
        int endIndex = startIndex + size;
        if (totalItem >= startIndex) {
            itemList = new ArrayList<>(allItemList.subList(startIndex, Math.min(endIndex, totalItem)));
        } else {
            itemList = new ArrayList<>();
        }
        this.timestamp = timestamp;
    }

    public Result(List<T> allItemList, CommonMethodHandle<String> idExtractor, String cursor, int size, String timestamp) {
        totalItem = allItemList.size();
        totalPage = totalItem % size == 0 ? totalItem / size : totalItem / size + 1;
        if (cursor == null) {
            itemList = allItemList.subList(0, Math.min(size, totalItem));
        } else {
            itemList = new ArrayList<>();
            int i = 0;
            for (int j = 0; j < totalItem; j++) {
                if (cursor.equals(idExtractor.invoke(allItemList.get(j)))) {
                    i = j;
                    break;
                }
            }
            for (int j = i + 1; j < totalItem && j < i + 1 + size; j++) {
                itemList.add(allItemList.get(j));
            }
        }
        this.timestamp = timestamp;
    }

    public List<T> getItemList() {
        return itemList;
    }

    public int getTotalItem() {
        return totalItem;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public String getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "Result{" +
                "itemList=" + itemList.toString() +
                ", totalItem=" + totalItem +
                ", totalPage=" + totalPage +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }
}
