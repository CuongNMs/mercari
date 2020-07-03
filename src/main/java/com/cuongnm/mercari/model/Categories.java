package com.cuongnm.mercari.model;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "categories")
public class Categories {

	@Id
	@GeneratedValue
	@Column(name = "category_id")
	private Long categoryId;

	@Column(name = "category_name")
	private String categoryName;

	@Column(name = "parent_category_id")
	private Long parentCategoryId;

	@ManyToMany(mappedBy = "categories")
	private Collection<Products> products;
	
	@OneToMany(mappedBy = "categories", cascade = CascadeType.ALL) 
	private Collection<Sizes> sizes;

	public Categories() {
		super();
	}

	public Categories(String categoryName, Long parentCategoryId) {
		this.categoryName = categoryName;
		this.parentCategoryId = parentCategoryId;
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

	public Long getParentCategoryId() {
		return parentCategoryId;
	}

	public void setParentCategoryId(Long parentCategoryId) {
		this.parentCategoryId = parentCategoryId;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

}
