package com.cuongnm.mercari.model;

import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "products")
public class Products {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "increment")
	@Column(name = "product_id")
	private Long productId;

	@Column(name = "product_name")
	private String productName;

	@Column(name = "price")
	private Long price;

	@Column(name = "product_described")
	private String productDescribed;

	@Column(name = "product_image_path")
	private String productImagePath;

	@Column(name = "product_video_path")
	private String productVideoPath;

	@Column(name = "state")
	private int state;

	@Column(name = "weight")
	private int weight;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "is_allow_offer")
	private boolean isAllowOffer;

	@Column(name = "quality")
	private int quality;

	@Column(name = "created_user")
	private long createdUser;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "brand_id")
	@JsonManagedReference
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Brands brands;

	@ManyToMany(mappedBy = "products", fetch = FetchType.LAZY)
	@JsonBackReference(value = "orders")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Collection<Orders> orders;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "product_category", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
	@JsonBackReference(value = "categories")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Collection<Categories> categories;

	public Products() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Products(String productName, Long price, String productDescribed, String productImagePath,
			String productVideoPath, int state, int weight, boolean isAllowOffer, int quality, long createdUser) {
		super();
		this.productName = productName;
		this.price = price;
		this.productDescribed = productDescribed;
		this.productImagePath = productImagePath;
		this.productVideoPath = productVideoPath;
		this.state = state;
		this.weight = weight;
		this.isAllowOffer = isAllowOffer;
		this.quality = quality;
		this.createdUser = createdUser;
	}

	public long getCreatedUser() {
		return createdUser;
	}

	public void setCreatedUser(long createdUser) {
		this.createdUser = createdUser;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
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

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate() {
		this.createdDate = new Date();
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

	public Brands getBrands() {
		return brands;
	}

	public void setBrands(Brands brands) {
		this.brands = brands;
	}

	public Collection<Orders> getOrders() {
		return orders;
	}

	public void setOrders(Collection<Orders> orders) {
		this.orders = orders;
	}

	public Collection<Categories> getCategories() {
		return categories;
	}

	public void setCategories(Collection<Categories> categories) {
		this.categories = categories;
	}

}
