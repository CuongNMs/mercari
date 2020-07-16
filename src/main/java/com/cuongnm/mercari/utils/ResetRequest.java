package com.cuongnm.mercari.utils;

import java.io.Serializable;

public class ResetRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4338047780741886401L;
	private String username;
	private String password;

	// need default constructor for JSON Parsing

	public String getUsername() {
		return this.username;
	}

	public ResetRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public ResetRequest(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

}
