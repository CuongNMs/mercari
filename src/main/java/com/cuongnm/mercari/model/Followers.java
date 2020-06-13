package com.cuongnm.mercari.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "followers", schema = "public")
public class Followers {
	
	@Id
	@GeneratedValue
	@Column(name = "follow_id", unique = true, nullable = false)
	private Long followId;

	@Column(name = "user_id", unique = true, nullable = false)
	private Long userId;

	@Column(name = "follow_user_id", unique = true, nullable = false)
	private Long followUserId;

	public Followers() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Followers(Long userId, Long followUserId) {
		super();
		this.userId = userId;
		this.followUserId = followUserId;
	}

	public Long getFollowId() {
		return followId;
	}

	public void setFollowId(Long followId) {
		this.followId = followId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getFollowUserId() {
		return followUserId;
	}

	public void setFollowUserId(Long followUserId) {
		this.followUserId = followUserId;
	}

}
