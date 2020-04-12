package com.iammybest.mongo.page;

/**
 * Created by MrDeng on 2017/3/23.
 */
public class MongoPage {
    int pageNo = 1;
    int pageSize = 20;
    String orderBy;
    ORDER order;
    private int skip = 0;
    private int limit = 20;

    public enum ORDER {
        DESC(-1),
        ADC(1);
        private int order;

        ORDER(int order) {
            this.order = order;
        }

        public int getOrder() {
            return this.order;
        }
    }

    public MongoPage pageNo(int pageNo) {
        this.pageNo = pageNo;
        return this;
    }

    public MongoPage pageSize(int pageSize) {
        this.pageSize = pageSize;
        this.limit = pageSize;
        return this;
    }

    public MongoPage orderBy(String orderBy) {
        this.orderBy = orderBy;
        return this;
    }

    public MongoPage order(ORDER order) {
        this.order = order;
        return this;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public int getOrder() {
        return order==null?1:order.getOrder();
    }

    public void setOrder(ORDER order) {
        this.order = order;
    }

    public void setSkip(int skip) {
        this.skip = skip;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getSkip() {
        return (pageNo - 1) * pageSize;
    }

    public int getLimit() {
        return limit;
    }
}
