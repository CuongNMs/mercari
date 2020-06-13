package com.cuongnm.mercari.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "news", schema = "public")
public class News {

	@Id
	@GeneratedValue
	@Column(name = "news_id", unique = true, nullable = false)
	private Long newsId;
	
	@Column(name = "title", unique = true, nullable = true)
    private String title;
	
	@Column(name = "content", unique = true, nullable = false)
    private String content;
	
	@Column(name = "created_date", unique = true, nullable = false)
    private String createdDate;

}
