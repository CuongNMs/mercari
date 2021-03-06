package com.cuongnm.mercari.utils;

import java.io.Serializable;

public class DefaultResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3983374505077698189L;

	private String code;
	private String message;
	private Object data;

	public DefaultResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DefaultResponse(String code, String message, Object data) {
		super();
		this.code = code;
		this.message = message;
		this.data = data;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
