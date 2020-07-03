package com.cuongnm.mercari.model;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "orders")
public class Orders {

	@Id
	@GeneratedValue
	@Column(name = "order_id")
	private Long orderId;

	@Column(name = "pay_type")
	private int payType;

	@Column(name = "address")
	private String address;

	@Column(name = "city")
	private String city;

	@Column(name = "status")
	private String status;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "created_user")
	private Long createdUser;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_date")
	private Date updatedDate;

	@Column(name = "updated_user")
	private Long updatedUser;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private Users users;

	@ManyToMany(mappedBy = "orders")
	private Collection<Products> products;

	public Orders() {

	}

	public Orders(int payType, String address, String city, String status, Long createdUser, Long updatedUser) {
		super();

		this.payType = payType;
		this.address = address;
		this.city = city;
		this.status = status;
		this.createdUser = createdUser;
		this.updatedUser = updatedUser;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	@PrePersist
	public void setCreatedDate(Date createdDate) {
		this.createdDate = new Date();
	}

	public Long getCreatedUser() {
		return createdUser;
	}

	@PrePersist
	public void setCreatedUser(Long createdUser) {
		this.createdUser = createdUser;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	@PrePersist
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = new Date();
	}

	public Long getUpdatedUser() {
		return updatedUser;
	}

	public void setUpdatedUser(Long updatedUser) {
		this.updatedUser = updatedUser;
	}

}
