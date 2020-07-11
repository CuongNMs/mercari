package com.cuongnm.mercari.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "reactions")
public class Reactions {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator="native")
	@GenericGenerator(
		    name = "native",
		    strategy = "increment"
		)
	@Column(name = "reaction_id")
	private Long reactionId;

	@Column(name = "reaction_type")
	private Integer reactionType;

	@Column(name = "subject")
	private String subject;

	@Column(name = "content")
	private String content;

	@Column(name = "is_like")
	private boolean isLike;

	@Column(name = "rate")
	private Integer rate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_date")
	private Date createdDate;

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate() {
		this.createdDate = new Date();
	}

	@ManyToOne
	@JoinColumn(name = "user_id") // thông qua khóa ngoại user_id
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Users users;
	
	@ManyToOne
	@JoinColumn(name = "product_id") // thông qua khóa ngoại user_id
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Products products;

	public Reactions() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getReactionId() {
		return reactionId;
	}

	public void setReactionId(Long reactionId) {
		this.reactionId = reactionId;
	}

	public Integer getReactionType() {
		return reactionType;
	}

	public void setReactionType(Integer reactionType) {
		this.reactionType = reactionType;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public boolean isLike() {
		return isLike;
	}

	public void setLike(boolean isLike) {
		this.isLike = isLike;
	}

	public Integer getRate() {
		return rate;
	}

	public void setRate(Integer rate) {
		this.rate = rate;
	}

}
