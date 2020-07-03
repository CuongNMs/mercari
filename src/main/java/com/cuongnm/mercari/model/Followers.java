package com.cuongnm.mercari.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "followers")
public class Followers {

	@Id
	@GeneratedValue
	@Column(name = "follow_id")
	private Long followId;

	@Column(name = "follow_user_id")
	private Long followUserId;

	@ManyToOne
	@JoinColumn(name = "user_id") // thông qua khóa ngoại user_id
	private Users users;

	public Followers() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Followers(Long followUserId) {
		super();
		this.followUserId = followUserId;
	}

	public Long getFollowId() {
		return followId;
	}

	public void setFollowId(Long followId) {
		this.followId = followId;
	}

	public Long getFollowUserId() {
		return followUserId;
	}

	public void setFollowUserId(Long followUserId) {
		this.followUserId = followUserId;
	}

}
