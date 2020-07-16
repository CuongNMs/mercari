package com.cuongnm.mercari.utils;

import java.io.Serializable;

public class NewsRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9095023668964544826L;

	private String title;

	private String content;

	public NewsRequest(String title, String content) {
		super();
		this.title = title;
		this.content = content;
	}

	public NewsRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
