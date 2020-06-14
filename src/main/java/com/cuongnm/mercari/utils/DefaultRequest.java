package com.cuongnm.mercari.utils;

import java.io.Serializable;

public class DefaultRequest implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3738822427789749127L;
	private String firstName;

	public DefaultRequest() {

	}

	public DefaultRequest(String firstName) {
		super();
		this.firstName = firstName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	
}
