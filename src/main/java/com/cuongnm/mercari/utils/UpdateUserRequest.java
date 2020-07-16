package com.cuongnm.mercari.utils;

import java.io.Serializable;

public class UpdateUserRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7039501442992850723L;

	private String email;

	private String phoneNumber;

	private Integer status;

	private String firstName;

	private String lastName;

	private String avatarPath;

	private String backgroundImagePath;

	private String address;

	private String city;

	private Long balance;

	public UpdateUserRequest(String email, String phoneNumber, Integer status, String firstName, String lastName,
			String avatarPath, String backgroundImagePath, String address, String city, Long balance) {
		super();
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.status = status;
		this.firstName = firstName;
		this.lastName = lastName;
		this.avatarPath = avatarPath;
		this.backgroundImagePath = backgroundImagePath;
		this.address = address;
		this.city = city;
		this.balance = balance;
	}

	public UpdateUserRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAvatarPath() {
		return avatarPath;
	}

	public void setAvatarPath(String avatarPath) {
		this.avatarPath = avatarPath;
	}

	public String getBackgroundImagePath() {
		return backgroundImagePath;
	}

	public void setBackgroundImagePath(String backgroundImagePath) {
		this.backgroundImagePath = backgroundImagePath;
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

	public Long getBalance() {
		return balance;
	}

	public void setBalance(Long balance) {
		this.balance = balance;
	}

}
