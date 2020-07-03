package com.cuongnm.mercari.utils;

import java.io.Serializable;

public class ExceptionResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 663675843507619135L;
	private String code;
	private String message;

	public ExceptionResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ExceptionResponse(String code, String message) {
		super();
		this.code = code;
		this.message = message;
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

}
