package com.nguyenmauhuy.orm.paging;

import java.util.List;

public class PageImpl<T> implements Page<T> {
    private int index;
    private int size;
    private long totalItems;
    private int totalPage;
    private List<T> data;

    public PageImpl() {
    }

    public PageImpl(int index, int size, long totalItem, List<T> data) {
        this.index = index;
        this.size = size;
        this.totalItems = totalItem;
        this.totalPage = getTotalPage();
        this.data = data;
    }

    @Override
    public int getIndex() {
        return index;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public long getTotalItem() {
        return totalItems;
    }

    @Override
    public int getTotalPage() {
        return (size == 0) ? 0 : (size > totalItems ? 1 : (int) Math.floor(totalItems / size));
    }

    @Override
    public List<T> getData() {
        return data;
    }
}
