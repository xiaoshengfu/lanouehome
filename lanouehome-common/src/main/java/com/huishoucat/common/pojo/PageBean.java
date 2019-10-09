package com.huishoucat.common.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * 分页对象
 * 
 * @author xiaoshengfu(2439323118@qq.com)
 * @description TODO
 * @date 2018年2月10日 上午10:32:41
 * @version V1.0
 * @param <T>
 */
public class PageBean<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	private int limit;// 每页显示行数
	private long total;// 总页数
	private int pageNum;// 当前页码
	private List<T> rows;// 当前页码数据

	public PageBean(int limit, long total, int pageNum, List<T> rows) {
		super();
		this.limit = limit;
		this.total = total;
		this.pageNum = pageNum;
		this.rows = rows;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}
}
