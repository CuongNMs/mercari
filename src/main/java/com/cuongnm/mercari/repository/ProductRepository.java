package com.cuongnm.mercari.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cuongnm.mercari.model.Products;

@Repository
public interface ProductRepository extends JpaRepository<Products, Long> {

	public void deleteById(Long productId);

	@Query(value = "SELECT * FROM products WHERE product_id IN (:productIds)", nativeQuery = true)
	public Set<Products> findByIds(Set<Long> productIds);

	@Query(value = "SELECT * FROM products LEFT JOIN product_category ON products.product_id = product_category.product_id WHERE product_category.category_id IN (:categoryId) "
			+ "AND products.brand_id IN (:brandId) AND products.price >= :priceMin AND products.price <= :priceMax", nativeQuery = true)
	public List<Products> filter(List<Long> categoryId, List<Long> brandId, Long priceMin, Long priceMax);

	@Query(value = "SELECT * FROM products WHERE product_name LIKE %:keyword% OR product_described LIKE %:keyword%", nativeQuery = true)
	public List<Products> searchByKeyword(String keyword);

}
