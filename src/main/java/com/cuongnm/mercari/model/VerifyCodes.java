package com.cuongnm.mercari.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "verify_codes", schema = "public")
public class VerifyCodes {
	@Id
	@GeneratedValue
	@Column(name = "verify_code_id", unique = true, nullable = false)
	private Long verifyCodeId;
	
	@Column(name = "verify_code", unique = false, nullable = true)
    private String verifyCode;
	
	@Column(name = "username", unique = true, nullable = false)
    private String username;

	public VerifyCodes() {

	}
	
	public VerifyCodes(String verifyCode, String username) {
		this.verifyCode = verifyCode;
		this.username = username;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	
	
}
