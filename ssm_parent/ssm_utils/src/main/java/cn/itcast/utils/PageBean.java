package cn.itcast.utils;

import java.util.List;

public class PageBean<T> {

	// 当前页
	private Integer pageNum;
	// 总记录数
	private Integer totalCount;
	// 每页数量
	private Integer pageSize;
	// 总页数
	private Integer totalPage;
	// 对象集合
	private List<T> list;

	public PageBean(Integer pageNum, Integer totalCount, Integer pageSize, Integer totalPage, List<T> list) {
		super();
		this.pageNum = pageNum;
		this.totalCount = totalCount;
		this.pageSize = pageSize;
		this.totalPage = totalPage;
		this.list = list;
	}

	public PageBean() {
		super();
	}

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
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

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	@Override
	public String toString() {
		return "PageBean [pageNum=" + pageNum + ", totalCount=" + totalCount + ", pageSize=" + pageSize + ", totalPage="
				+ totalPage + ", list=" + list + "]";
	}

}
