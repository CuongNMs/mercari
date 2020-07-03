package com.cuongnm.mercari.utils;

import java.io.Serializable;

public class JwtResponse implements Serializable {

	private static final long serialVersionUID = -8091879091924046844L;
	private final String jwtToken;
	private final String userId;
	private final String userName;
	private final String avatar;
	public JwtResponse(String userId, String userName, String jwtToken, String avatar) {
		this.jwtToken = jwtToken;
		this.userId = userId;
		this.userName = userName;
		this.avatar = avatar;
	}

	public String getJwtToken() {
		return this.jwtToken;
	}

	public String getUserId() {
		return this.userId;
	}

	public String getUserName() {
		return this.userName;
	}

	public String getAvatar() {
		return this.avatar;
	}
	
	
}