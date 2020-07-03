package com.cuongnm.mercari.utils;

import java.io.Serializable;

public class RateRequest implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7459291722596884895L;
	private Long targetUserId;
	private int level;

	public RateRequest(Long targetUserId, int level) {
		super();
		this.targetUserId = targetUserId;
		this.level = level;
	}

	public RateRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getTargetUserId() {
		return targetUserId;
	}

	public void setTargetUserId(Long targetUserId) {
		this.targetUserId = targetUserId;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

}
