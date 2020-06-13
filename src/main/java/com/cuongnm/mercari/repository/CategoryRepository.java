package com.cuongnm.mercari.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cuongnm.mercari.model.Categories;


@Repository
public interface CategoryRepository extends JpaRepository<Categories, Long>{

	
	@Query(value = "WITH RECURSIVE rcte as " + 
			"(SELECT category_id, category_name, parent_category_id FROM categories WHERE category_id = ?1 " + 
			"UNION " + 
			"SELECT c.category_id, c.category_name, c.parent_category_id FROM categories c " + 
			"JOIN rcte r ON c.parent_category_id = r.category_id " + 
			") " + 
			"SELECT category_id, category_name, parent_category_id FROM rcte", nativeQuery = true)
	List<Categories> findByCategoryId(Long categoryId);
	
}
