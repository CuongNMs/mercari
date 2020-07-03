package com.cuongnm.mercari.utils;

import java.io.Serializable;

public class ChangePasswordRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4094789196391226569L;
	private String oldPassword;
	private String newPassword;

	public ChangePasswordRequest() {

	}

	public ChangePasswordRequest(String oldPassword, String newPassword) {
		super();
		this.oldPassword = oldPassword;
		this.newPassword = newPassword;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

}
