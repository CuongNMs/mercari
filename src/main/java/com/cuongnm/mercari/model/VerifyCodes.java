package com.cuongnm.mercari.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "verify_codes")
public class VerifyCodes {
	@Id
	@GeneratedValue
	@Column(name = "verify_code_id")
	private Long verifyCodeId;

	@Column(name = "verify_code")
	private String verifyCode;

	@OneToOne
	@JoinColumn(name = "user_id")
	private Users users;

	public VerifyCodes() {

	}

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public VerifyCodes(String verifyCode) {
		this.verifyCode = verifyCode;
	}

	public Long getVerifyCodeId() {
		return verifyCodeId;
	}

	public void setVerifyCodeId(Long verifyCodeId) {
		this.verifyCodeId = verifyCodeId;
	}

	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

}
