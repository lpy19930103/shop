package com.shop.common.pojo;

import java.io.Serializable;
/**
 * datagrid 展示数据的POJO 包括商品的POJO
 * @title EasyResult.java
 * <p>description</p>
 * <p>company: www.itheima.com</p>
 * @author ljh
 * @version 1.0
 */
import java.util.List;

public class EasyResult<T> implements Serializable {

    private static final long serialVersionUID = 4742258723011001853L;
    private Long total;
    private Integer status;
    private String msg;
    private List<T> rows;
    private T DTO;

    public EasyResult() {
    }

    public EasyResult(Integer status) {
        this.status = status;
    }

    public EasyResult(T DTO) {
        this(null, null, DTO);
    }

    public EasyResult(Integer status, String msg) {
        this(status, msg, null);
    }

    public EasyResult(Integer status, String msg, T DTO) {
        this.status = status;
        this.msg = msg;
        this.DTO = DTO;
    }


    public EasyResult(List<T> rows) {
        this(null, rows);
    }

    public EasyResult(Long total, List<T> rows) {
        this(null, total, rows);
    }

    public EasyResult(Integer status, Long total, List<T> rows) {
        this(total, status, null, rows);
    }

    public EasyResult(Long total, Integer status, String msg, List<T> rows) {
        this.total = total;
        this.status = status;
        this.msg = msg;
        this.rows = rows;
    }


    public T getDTO() {
        return DTO;
    }

    public void setDTO(T DTO) {
        this.DTO = DTO;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

}
