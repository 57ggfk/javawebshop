package com.heima.domain;

import java.util.List;

public class PageBean<T> {
	
	private Integer pageNumber;		//当前页
	private Integer pageSize;		//每一页的商品个数（传给数据库）
	private Integer totalPage;		//总页数
	private Integer totalRecord;		//商品总个数
	private Integer startIndex;		//起始索引（传给数据库）
	private List<T> data;			//当前页要显示的数据
		
	public PageBean() {
		super();
	}
	
	public PageBean(Integer pageNumber, Integer pageSize, Integer totalCount) {
		super();
		this.pageNumber = pageNumber;
		this.pageSize = pageSize;
		this.totalRecord = totalCount; //数据库查询
		//#1 总页数 = (商品个数 / 每页个数 )向上去整数。
		this.totalPage = (int) Math.ceil(1.0*totalCount / pageSize);
		//#2 起始索引 = (当前页-1)*每页个数
		startIndex = (pageNumber-1)*pageSize;
	}

	public Integer getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

	public Integer getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(Integer totalRecord) {
		this.totalRecord = totalRecord;
	}

	public Integer getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(Integer startIndex) {
		this.startIndex = startIndex;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}
	

		
}
