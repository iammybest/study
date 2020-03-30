package com.iammybest.springboot.scheduler.core;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

/**
 * @DESCRIBE:
 * @TIME: 2019/9/24 15:12
 * @AUTHOR: qinghai.deng
 **/
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response<T> implements Serializable {
    private static final long serialVersionUID = -6602365878131231511L;
    private Response.Status status;
    private String errorCode;
    private String errorMessage;
    private String extMessage;
    private Long total;
    private Long totalCount;
    private Long pageIndex;
    private Long pageSize;
    private Long pageCount;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    public Response() {
        this.status = Response.Status.SUCCEED;
    }

    public Response(IError error) {
        this.status = Response.Status.SUCCEED;
        this.errorCode = error.getErrorCode();
        this.errorMessage = error.getErrorMessage();
        this.status = Response.Status.FAILED;
    }

    public Response(Long total) {
        this.status = Response.Status.SUCCEED;
        this.total = total;
    }

    public Response(T data) {
        this.status = Response.Status.SUCCEED;
        this.data = data;
    }

    public Response(T data, Long total) {
        this.status = Response.Status.SUCCEED;
        this.data = data;
        this.total = total;
    }

    public Response(Long total, T data) {
        this.status = Response.Status.SUCCEED;
        this.data = data;
        this.total = total;
    }

    public static Response create() {
        return new Response();
    }

    public static Response create(IError error) {
        Response response = new Response();
        response.errorCode = error.getErrorCode();
        response.errorMessage = error.getErrorMessage();
        response.status = Response.Status.FAILED;
        return response;
    }

    public static Response create(Object data) {
        return new Response(data);
    }

    public static Response create(Long total) {
        return new Response(total);
    }

    public static Response create(Object data, Long total) {
        return new Response(data, total);
    }

    public static Response create(Long total, Object data) {
        return new Response(data, total);
    }

    public Response.Status getStatus() {
        return this.status;
    }

    public void setStatus(Response.Status status) {
        this.status = status;
    }

    public String getErrorCode() {
        return this.errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getExtMessage() {
        return this.extMessage;
    }

    public void setExtMessage(String extMessage) {
        this.extMessage = extMessage;
    }

    public Long getTotal() {
        return this.total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    /** @deprecated */
    @Deprecated
    public Long getTotalCount() {
        return this.totalCount;
    }

    /** @deprecated */
    @Deprecated
    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

    /** @deprecated */
    @Deprecated
    public Long getPageIndex() {
        return this.pageIndex;
    }

    /** @deprecated */
    @Deprecated
    public void setPageIndex(Long pageIndex) {
        this.pageIndex = pageIndex;
    }

    /** @deprecated */
    @Deprecated
    public Long getPageSize() {
        return this.pageSize;
    }

    /** @deprecated */
    @Deprecated
    public void setPageSize(Long pageSize) {
        this.pageSize = pageSize;
    }

    /** @deprecated */
    @Deprecated
    public Long getPageCount() {
        return this.pageCount;
    }

    /** @deprecated */
    @Deprecated
    public void setPageCount(Long pageCount) {
        this.pageCount = pageCount;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.getErrorCode() != null) {
            sb.append("{status: ").append(this.getStatus()).append(", errorCode : ").append(this.getErrorCode()).append(", errorMessage : ").append(this.getErrorMessage()).append(", extMessage : ").append(this.getExtMessage()).append("}");
        } else {
            sb.append("Succeed");
        }

        return sb.toString();
    }

    public static enum Status {
        SUCCEED,
        FAILED;

        private Status() {
        }
    }
}
