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
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "increment")
	@Column(name = "search_condition_id")
	private Long searchConditionId;

	@Column(name = "keyword")
	private String keyword;

	@Column(name = "price_min")
	private Long priceMin;

	@Column(name = "price_max")
	private Long priceMax;

	@OneToOne
	@JoinColumn(name = "user_id")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Users users;

	public SearchConditions(String keyword, Long priceMin, Long priceMax, Users users) {
		super();
		this.keyword = keyword;
		this.priceMin = priceMin;
		this.priceMax = priceMax;
		this.users = users;
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

}
