package com.cuongnm.mercari.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cuongnm.mercari.model.Brands;


public interface BrandRepository extends JpaRepository<Brands, Long>{
	
}
