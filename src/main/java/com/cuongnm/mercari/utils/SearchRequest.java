package com.cuongnm.mercari.utils;

import java.io.Serializable;
import java.util.List;

public class SearchRequest implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7396661658457118764L;
	private String keyword;
	private List<Long> categoryId;
	private List<Long> brandId;
	private Long priceMax;
	private Long priceMin;

	public SearchRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SearchRequest(String keyword, List<Long> categoryId, List<Long> brandId, Long priceMax, Long priceMin) {
		super();
		this.keyword = keyword;
		this.categoryId = categoryId;
		this.brandId = brandId;
		this.priceMax = priceMax;
		this.priceMin = priceMin;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public List<Long> getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(List<Long> categoryId) {
		this.categoryId = categoryId;
	}

	public List<Long> getBrandId() {
		return brandId;
	}

	public void setBrandId(List<Long> brandId) {
		this.brandId = brandId;
	}

	public Long getPriceMax() {
		return priceMax;
	}

	public void setPriceMax(Long priceMax) {
		this.priceMax = priceMax;
	}

	public Long getPriceMin() {
		return priceMin;
	}

	public void setPriceMin(Long priceMin) {
		this.priceMin = priceMin;
	}

}
