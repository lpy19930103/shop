package com.shop.common.pojo;

import java.io.Serializable;
/**
 * datagrid 展示数据的POJO 包括商品的POJO
 * @title EasyUIDataGridResult.java
 * <p>description</p>
 * <p>company: www.itheima.com</p>
 * @author ljh 
 * @version 1.0
 */
import java.util.List;

public class EasyUIDataGridResult<T> implements Serializable {

	private static final long serialVersionUID = 4742258723011001853L;
	private Long total;
	
	private List<T>  rows;

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public List<T>  getRows() {
		return rows;
	}

	public void setRows(List<T>  rows) {
		this.rows = rows;
	}
	
}
