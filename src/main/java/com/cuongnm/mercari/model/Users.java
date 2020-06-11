package com.cuongnm.mercari.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users", schema = "public")
public class Users {
	
	@Id
	@GeneratedValue
	@Column(name = "user_id", unique = true, nullable = false)
	private Long userId;
	
	@Column(name = "email", unique = true, nullable = true)
    private String email;
	
	@Column(name = "username", unique = true, nullable = false)
    private String username;
	
	@Column(name = "password", unique = true, nullable = false)
    private String password;
    
	@Column(name = "phone_number", unique = true, nullable = true)
    private String phoneNumber;
    
	@Column(name = "status", unique = true, nullable = true)
    private Integer status;
    
	@Column(name = "first_name", nullable = true)
    private String firstName;
    
	@Column(name = "last_name", nullable = true)
    private String lastName;
    
	@Column(name = "avatar", nullable = true)
    private byte[] avatar;
    
	@Column(name = "background_image", nullable = true)
    private byte[] backgroundImage;
    
	@Column(name = "address", nullable = true)
    private String address;
    
	@Column(name = "city", nullable = true)
    private String city;
    
	@Column(name = "is_online", nullable = true)
    private boolean isOnline;
    
	@Column(name = "balance", nullable = true)
    private Long balance;
    
	@Column(name = "is_vacation_mode", nullable = true)
    private boolean isVacationMode;
    
	@Column(name = "role", nullable = true)
    private Integer role;
    
	@Column(name = "is_auto_withdraw", nullable = true)
    private boolean isAutoWithdraw;
    
	public Users() {
		super();
	}
	
	
	public Users(Long userId,  String username, String password) {
		this.userId = userId;
		this.username = username;
		this.password = password;
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
	
	
	public byte[] getAvatar() {
		return avatar;
	}
	public void setAvatar(byte[] avatar) {
		this.avatar = avatar;
	}
	
	public byte[] getBackgroundImage() {
		return backgroundImage;
	}
	public void setBackgroundImage(byte[] backgroundImage) {
		this.backgroundImage = backgroundImage;
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
	
	
	public boolean isOnline() {
		return isOnline;
	}
	public void setOnline(boolean isOnline) {
		this.isOnline = isOnline;
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
	
	
	public Integer getRole() {
		return role;
	}
	public void setRole(Integer role) {
		this.role = role;
	}
	
	
	public boolean isAutoWithdraw() {
		return isAutoWithdraw;
	}
	public void setAutoWithdraw(boolean isAutoWithdraw) {
		this.isAutoWithdraw = isAutoWithdraw;
	}
	
    
}
