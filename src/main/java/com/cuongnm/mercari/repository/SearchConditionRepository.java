package com.cuongnm.mercari.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cuongnm.mercari.model.SearchConditions;

public interface SearchConditionRepository extends JpaRepository<SearchConditions, Long> {
	
}
