package com.cuongnm.mercari.utils;

import java.io.Serializable;

public class ProductRequest implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 114702202253818327L;
	private String productName;
	private long price;
	private String productDescribed;
	private String productImagePath;
	private String productVideoPath;
	private int size;
	private long brandId;
	private long categoryId;
	private int state;
	private int weight;
	private boolean isAllowOffer;
	private int quality;

	public ProductRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProductRequest(String productName, long price, String productDescribed, String productImagePath,
			String productVideoPath, int size, long brandId, long categoryId, int state, int weight,
			boolean isAllowOffer, int quality) {
		super();
		this.productName = productName;
		this.price = price;
		this.productDescribed = productDescribed;
		this.productImagePath = productImagePath;
		this.productVideoPath = productVideoPath;
		this.size = size;
		this.brandId = brandId;
		this.categoryId = categoryId;
		this.state = state;
		this.weight = weight;
		this.isAllowOffer = isAllowOffer;
		this.quality = quality;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	public String getProductDescribed() {
		return productDescribed;
	}

	public void setProductDescribed(String productDescribed) {
		this.productDescribed = productDescribed;
	}

	public String getProductImagePath() {
		return productImagePath;
	}

	public void setProductImagePath(String productImagePath) {
		this.productImagePath = productImagePath;
	}

	public String getProductVideoPath() {
		return productVideoPath;
	}

	public void setProductVideoPath(String productVideoPath) {
		this.productVideoPath = productVideoPath;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public long getBrandId() {
		return brandId;
	}

	public void setBrandId(long brandId) {
		this.brandId = brandId;
	}

	public long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public boolean isAllowOffer() {
		return isAllowOffer;
	}

	public void setAllowOffer(boolean isAllowOffer) {
		this.isAllowOffer = isAllowOffer;
	}

	public int getQuality() {
		return quality;
	}

	public void setQuality(int quality) {
		this.quality = quality;
	}

}
