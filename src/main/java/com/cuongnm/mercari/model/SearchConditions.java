package com.cuongnm.mercari.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "search_conditions")
public class SearchConditions {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator="native")
	@GenericGenerator(
		    name = "native",
		    strategy = "increment"
		)
	@Column(name = "search_condition_id")
	private Long searchConditionId;

	@Column(name = "keyword")
	private String keyword;

	@Column(name = "priceMin")
	private Long priceMin;

	@Column(name = "priceMax")
	private Long priceMax;

	@Column(name = "quality")
	private int quality;

	@Column(name = "state")
	private int state;
	
	@OneToOne 
    @JoinColumn(name = "user_id") 
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Users users;

	public SearchConditions(String keyword, Long priceMin, Long priceMax, int quality, int state) {
		super();
		this.keyword = keyword;
		this.priceMin = priceMin;
		this.priceMax = priceMax;
		this.quality = quality;
		this.state = state;
	}

	public SearchConditions() {
		super();
	}

	public Long getSearchConditionId() {
		return searchConditionId;
	}

	public void setSearchConditionId(Long searchConditionId) {
		this.searchConditionId = searchConditionId;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public Long getPriceMin() {
		return priceMin;
	}

	public void setPriceMin(Long priceMin) {
		this.priceMin = priceMin;
	}

	public Long getPriceMax() {
		return priceMax;
	}

	public void setPriceMax(Long priceMax) {
		this.priceMax = priceMax;
	}

	public int getQuality() {
		return quality;
	}

	public void setQuality(int quality) {
		this.quality = quality;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

}
