package com.sanji.mall.common.util;

public class Pagination {

	private Long rows;// 每页显示行数
	private Long page;// 当前页数
	private Long totalCount;// 总记录数
	private Long totalPage;// 总页数
	private boolean hasNextPage;// 下一页
	private boolean hasPreviousPage;// 上一页
	
	/**
	 * 值初始化
	 */	
	public Pagination() {
		rows = 10L;
		page = 0L;
		totalCount = 0L;
		totalPage = 0L;
		hasNextPage = false;
		hasPreviousPage = false;
	}

	/**
	 * 获取行数
	 */
	public Long getRows() {
		return rows;
	}
	
	/**
	 * 获取当前页数
	 */
	public Long getCurrPage() {
		return page;
	}
	
	/**
	 * 获取下一页
	 */
	public Long getNextPage() {
		return page + 1;
	}
	
	/**
	 * 获取指定的页数
	 */
	public Long getPage() {
		return page;
	}
	
	/**
	 * 获取上一页
	 */
	public Long getPreviousPage() {
		return page - 1;
	}
	
	/**
	 * 获取总记录数
	 */	
	public Long getTotalCount() {
		return totalCount;
	}
	
	/**
	 * 获取总页数
	 */
	public Long getTotalPage() {
		totalPage = totalCount / rows;
		if (totalCount % rows > 0 || totalPage == 0)
			totalPage++;
		return totalPage;
	}

	/**
	 * 给行数赋值,即一页rows行
	 */
	public void setRows(Long rows) {
		this.rows = rows;
	}
	
	/**
	 * 给页数赋值，即当前页数
	 */
	public void setPage(Long page) {
		this.page = page;
	}
	
	/**
	 * 给总记录数赋值，即查询的总记录数
	 */
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	
	/**
	 * 判断还有没有下一页，即是否已到最后一页
	 */
	public boolean isHasNextPage() {
		if (getCurrPage() >= getTotalPage())
			hasNextPage = false;
		else
			hasNextPage = true;
		return hasNextPage;
	}

	/**
	 * 判断还有没有上一页，即是否已到第一页
	 */
	public boolean isHasPreviousPage() {
		if (getCurrPage() - 1 > 0)
			hasPreviousPage = true;
		else
			hasPreviousPage = false;
		return hasPreviousPage;
	}
}
