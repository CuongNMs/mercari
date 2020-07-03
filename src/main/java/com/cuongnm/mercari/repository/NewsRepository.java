package com.cuongnm.mercari.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cuongnm.mercari.model.News;

@Repository
public interface NewsRepository extends JpaRepository<News, Long>{
	
}
