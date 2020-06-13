package com.cuongnm.mercari.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cuongnm.mercari.model.News;

public interface NewsRepository extends JpaRepository<News, Long>{
	
}
