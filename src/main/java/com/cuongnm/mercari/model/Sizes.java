package com.cuongnm.mercari.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "sizes")
public class Sizes {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator="native")
	@GenericGenerator(
		    name = "native",
		    strategy = "increment"
		)
	@Column(name = "size_id")
	private Long sizeId;

	@Column(name = "size_name")
	private String sizeName;

	@ManyToOne
	@JoinColumn(name = "category_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Categories categories;
	
	@OneToOne
    @JoinColumn(name = "user_id") 
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Users users;

	public Sizes() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Sizes(String sizeName) {
		super();
		this.sizeName = sizeName;
	}

	public Long getSizeId() {
		return sizeId;
	}

	public void setSizeId(Long sizeId) {
		this.sizeId = sizeId;
	}

	public String getSizeName() {
		return sizeName;
	}

	public void setSizeName(String sizeName) {
		this.sizeName = sizeName;
	}

}
