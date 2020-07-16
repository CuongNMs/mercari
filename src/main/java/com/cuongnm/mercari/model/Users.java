package com.cuongnm.mercari.model;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "users")
public class Users {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "increment")
	@Column(name = "user_id")
	private Long userId;

	@Column(name = "email", unique = true, nullable = true)
	private String email;

	@Column(name = "username", unique = true, nullable = false)
	private String username;

	@Column(name = "password", nullable = false)
	@JsonIgnore
	private String password;

	@Column(name = "phone_number", nullable = false)
	private String phoneNumber;

	@Column(name = "status")
	private Integer status;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "avatar_path")
	private String avatarPath;

	@Column(name = "background_image_path")
	private String backgroundImagePath;

	@Column(name = "address")
	private String address;

	@Column(name = "city")
	private String city;

	@Column(name = "balance")
	private Long balance;

	@Column(name = "is_vacation_mode")
	private boolean isVacationMode;

	@Column(name = "role")
	private int role;

	@Column(name = "is_auto_withdraw")
	private boolean isAutoWithdraw;

	@OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Collection<Followers> followers;

	@OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Collection<Reactions> reactions;

	@OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Collection<Orders> orders;

	@OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Collection<News> news;

	public Users() {
		super();
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public boolean isVacationMode() {
		return isVacationMode;
	}

	public void setVacationMode(boolean isVacationMode) {
		this.isVacationMode = isVacationMode;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	public boolean isAutoWithdraw() {
		return isAutoWithdraw;
	}

	public void setAutoWithdraw(boolean isAutoWithdraw) {
		this.isAutoWithdraw = isAutoWithdraw;
	}

	@Override
	public String toString() {
		return "Users [userId=" + userId + ", email=" + email + ", username=" + username + ", password=" + password
				+ ", phoneNumber=" + phoneNumber + ", status=" + status + ", firstName=" + firstName + ", lastName="
				+ lastName + ", avatarPath=" + avatarPath + ", backgroundImagePath=" + backgroundImagePath
				+ ", address=" + address + ", city=" + city + ", balance=" + balance + ", isVacationMode="
				+ isVacationMode + ", role=" + role + ", isAutoWithdraw=" + isAutoWithdraw + ", followers=" + followers
				+ ", reactions=" + reactions + ", orders=" + orders + "]";
	}

}
