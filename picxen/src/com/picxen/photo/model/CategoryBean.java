package com.picxen.photo.model;

public class CategoryBean {
	private int categoryNo;
	private String categoryName;
	private int categoryOrder;
	
	//pop new upcom filter name
	private String filterName;
	
	
	
	
	public String getFilterName() {
		return filterName;
	}
	public void setFilterName(String filterName) {
		this.filterName = filterName;
	}
	public int getCategoryNo() {
		return categoryNo;
	}
	public void setCategoryNo(int categoryNo) {
		this.categoryNo = categoryNo;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public int getCategoryOrder() {
		return categoryOrder;
	}
	public void setCategoryOrder(int categoryOrder) {
		this.categoryOrder = categoryOrder;
	}
	
	@Override
	public String toString() {
		return "CategoryBean [categoryNo=" + categoryNo + ", categoryName="
				+ categoryName + ", categoryOrder=" + categoryOrder
				+ ", filterName=" + filterName + "]";
	}
	
	
}////
