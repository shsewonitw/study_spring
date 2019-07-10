package com.tje.webapp.setting;

public class PagingInfo {
	private int pagingSize;
	private int pageRange;

	
	
	public PagingInfo() {
	}

	public PagingInfo(int pagingSize) {
		this.pagingSize = pagingSize;
	}

	public int getPagingSize() {
		return pagingSize;
	}

	public void setPagingSize(int pagingSize) {
		this.pagingSize = pagingSize;
	}

	public int getPageRange() {
		return pageRange;
	}

	public void setPageRange(int pageRange) {
		this.pageRange = pageRange;
	}
	
	
}
