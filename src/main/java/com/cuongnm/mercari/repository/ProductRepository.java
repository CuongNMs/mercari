package com.cuongnm.mercari.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cuongnm.mercari.model.Products;

@Repository
public interface ProductRepository extends JpaRepository<Products, Long>{
	
	public void deleteById(Long productId);
	
}
