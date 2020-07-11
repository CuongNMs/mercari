package com.cuongnm.mercari.model;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "categories")
public class Categories {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "increment")
	@Column(name = "category_id")
	private Long categoryId;

	@Column(name = "category_name")
	private String categoryName;

	@OneToMany(mappedBy = "parentCategory", fetch = FetchType.LAZY)
	@JsonBackReference
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Collection<Categories> children;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_category_id", referencedColumnName = "category_id")
	@JsonManagedReference
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Categories parentCategory;

	
	
	
	
	@ManyToMany(mappedBy = "categories", fetch = FetchType.LAZY)
	@JsonBackReference
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Collection<Products> products;

	
	
	
	@OneToMany(mappedBy = "categories", cascade = CascadeType.ALL)
	@JsonBackReference
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Collection<Sizes> sizes;

	public Categories() {
		super();
	}

	public Categories(String categoryName) {
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

	public Categories getParentCategory() {
		return parentCategory;
	}

	public void setParentCategory(Categories parentCategory) {
		this.parentCategory = parentCategory;
	}

	public Collection<Products> getProducts() {
		return products;
	}

	public void setProducts(Collection<Products> products) {
		this.products = products;
	}

	public Collection<Sizes> getSizes() {
		return sizes;
	}

	public void setSizes(Collection<Sizes> sizes) {
		this.sizes = sizes;
	}

}
