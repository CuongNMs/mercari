package com.cuongnm.mercari.utils;

import java.io.Serializable;

public class CategoryRequest implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8108644847500020713L;
	private String categoryName;
	private long parentCategoryId;

	public CategoryRequest(String categoryName, long parentCategoryId) {
		super();
		this.categoryName = categoryName;
		this.parentCategoryId = parentCategoryId;
	}

	public CategoryRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public long getParentCategoryId() {
		return parentCategoryId;
	}

	public void setParentCategoryId(long parentCategoryId) {
		this.parentCategoryId = parentCategoryId;
	}

}
