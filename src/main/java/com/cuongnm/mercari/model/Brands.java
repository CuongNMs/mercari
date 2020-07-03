package com.cuongnm.mercari.model;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "brands")
public class Brands {
	@Id
	@GeneratedValue
	@Column(name = "brand_id")
	private Long brandId;

	@Column(name = "brand_name")
	private String brandName;
	
	@OneToMany(mappedBy = "brands", cascade = CascadeType.ALL) // Quan hệ 1-n với đối tượng ở dưới (Person) (1 địa điểm có nhiều người ở)
    private Collection<Products> products;
	

	public Brands(String brandName) {
		
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
