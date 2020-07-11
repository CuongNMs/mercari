package com.cuongnm.mercari.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "followers")
public class Followers {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "increment")
	@Column(name = "follow_id")
	private Long followId;

	@ManyToOne
	@JoinColumn(name = "user_id")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Users users;

	@Column(name = "follow_user_target")
	private Long followUserTarget;

	@Column(name = "follow_product_target")
	private Long followProductTarget;

	public Followers() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getFollowId() {
		return followId;
	}

	public void setFollowId(Long followId) {
		this.followId = followId;
	}

	public Long getFollowUserTarget() {
		return followUserTarget;
	}

	public void setFollowUserTarget(Long followUserTarget) {
		this.followUserTarget = followUserTarget;
	}

	public Long getFollowProductTarget() {
		return followProductTarget;
	}

	public void setFollowProductTarget(Long followProductTarget) {
		this.followProductTarget = followProductTarget;
	}

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

}
