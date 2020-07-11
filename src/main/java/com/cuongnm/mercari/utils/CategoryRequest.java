package com.cuongnm.mercari.utils;

import java.io.Serializable;

public class CategoryRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5061708867119454552L;
	private String categoryName;
	private Long parentCategoryId;

	public CategoryRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CategoryRequest(String categoryName, Long parentCategoryId) {
		super();
		this.categoryName = categoryName;
		this.parentCategoryId = parentCategoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Long getParentCategoryId() {
		return parentCategoryId;
	}

	public void setParentCategoryId(Long parentCategoryId) {
		this.parentCategoryId = parentCategoryId;
	}

}
