package com.anglewang.entity;

import java.io.Serializable;

public class Category implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String categoryId;//书籍类别ID
	private String categoryName;//书籍类别民称
	
	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
}
