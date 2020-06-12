package com.cuongnm.mercari.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "categories", schema = "public")
public class Categories {

	@Id
	@GeneratedValue
	@Column(name = "category_id", unique = true, nullable = false)
	private Long categoryId;

	@Column(name = "category_name", unique = true, nullable = true)
	private String categoryName;

	@Column(name = "parent_category_id", unique = true, nullable = false)
	private String parentCategoryId;

	public Categories() {
		super();

	}

	public Categories(Long categoryId, String categoryName) {
		super();
		this.categoryId = categoryId;
		this.categoryName = categoryName;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

}
