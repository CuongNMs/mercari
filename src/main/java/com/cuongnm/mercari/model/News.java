package com.cuongnm.mercari.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "news")
public class News {

	@Id
	@GeneratedValue
	@Column(name = "news_id")
	private Long newsId;

	@Column(name = "title")
	private String title;

	@Column(name = "content")
	private String content;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_date")
	private Date createdDate;

	@ManyToOne
	@JoinColumn(name = "user_id") // thông qua khóa ngoại user_id
	private Users users;
	


	public News() {

	}

	public Long getNewsId() {
		return newsId;
	}

	public void setNewsId(Long newsId) {
		this.newsId = newsId;
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

	public Date getCreatedDate() {
		return createdDate;
	}
	@PrePersist
	public void setCreatedDate() {
		this.createdDate = new Date();
	}

}
