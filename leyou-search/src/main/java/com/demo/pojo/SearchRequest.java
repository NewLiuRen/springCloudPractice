package com.demo.pojo;

import java.util.Map;

public class SearchRequest {
    // 搜索条件
    private String key;
    // 当前页数
    private Integer page;
    // 排序字段
    private String sortBy;
    // 是否降序
    private Boolean descending;
    // 过滤条件
    private Map<String, Object> filter;

    // 每页商品数量
    private static final Integer DEFAULT_SIZE = 20;
    // 默认页数
    private static final Integer DEFAULT_PAGE = 1;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Integer getPage() {
        if (this.page == null) return DEFAULT_PAGE;
        return Math.max(this.page, DEFAULT_PAGE);
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return DEFAULT_SIZE;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public Boolean getDescending() {
        return descending;
    }

    public void setDescending(Boolean descending) {
        this.descending = descending;
    }

    public Map<String, Object> getFilter() {
        return filter;
    }

    public void setFilter(Map<String, Object> filter) {
        this.filter = filter;
    }
}
