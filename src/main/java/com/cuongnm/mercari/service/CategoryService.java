package com.cuongnm.mercari.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cuongnm.mercari.model.Categories;
import com.cuongnm.mercari.repository.CategoryRepository;


@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	// private Categories category;
	
	public List<Categories> loadCategoryByCategoryId(Long categoryId) {
		
		List<Categories> results = categoryRepository.findByCategoryId(categoryId);
		
		results.forEach(c -> {
	        System.out.println(c.getCategoryId());
	    });
		
		return results;
	}
	
}
