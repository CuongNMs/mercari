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

@Entity
@Table(name = "orders")
public class Orders {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "increment")
	@Column(name = "order_id")
	private Long orderId;

	@Column(name = "pay_type")
	private int payType;

	@Column(name = "address")
	private String address;

	@Column(name = "city")
	private String city;

	@Column(name = "status")
	private int status;

	@Column(name = "amount")
	private Long amount;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_date")
	private Date createdDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_date")
	private Date updatedDate;

	@ManyToOne
	@JoinColumn(name = "user_id")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Users users;

	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "order_product", 
			joinColumns = @JoinColumn(name = "order_id"), 
			inverseJoinColumns = @JoinColumn(name = "product_id") 
	)
	@JsonBackReference
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Collection<Products> products;
	
	
//	@ManyToMany(mappedBy = "orders", fetch = FetchType.LAZY)
//	@JsonBackReference
//	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
//	private Collection<Products> products;

	public Orders() {

	}

	public Orders(int payType, String address, String city, int status) {
		this.payType = payType;
		this.address = address;
		this.city = city;
		this.status = status;
	}

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public Collection<Products> getProducts() {
		return products;
	}

	public void setProducts(Collection<Products> products) {
		this.products = products;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public int getPayType() {
		return payType;
	}

	public void setPayType(int payType) {
		this.payType = payType;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate() {
		this.createdDate = new Date();
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate() {
		this.updatedDate = new Date();
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

}
