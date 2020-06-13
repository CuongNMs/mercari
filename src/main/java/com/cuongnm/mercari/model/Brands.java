package com.cuongnm.mercari.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "brands", schema = "public")
public class Brands {
	@Id
	@GeneratedValue
	@Column(name = "brand_id", unique = true, nullable = false)
	private Long brandId;

	@Column(name = "brand_name", unique = true, nullable = false)
	private String brandName;

	public Brands(String brandName) {
		super();
		this.brandName = brandName;
	}

	public Brands() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getBrandId() {
		return brandId;
	}

	public void setBrandId(Long brandId) {
		this.brandId = brandId;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

}
