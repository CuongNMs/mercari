package com.cuongnm.mercari.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cuongnm.mercari.model.Categories;
import com.cuongnm.mercari.service.CategoryService;
import com.cuongnm.mercari.service.UtilService;

@RestController
public class UserController{

	@Autowired
	private UtilService service;
	
	@Autowired
	private CategoryService cService;
	
	
	@RequestMapping({ "/create-code-reset-password" })
	public String firstPage() {
		return service.createCodeVerify();
	}
	
	@RequestMapping({ "/ctest" })
	public List<Categories> getCategory() {
		return cService.loadCategoryByCategoryId(1L);
	}

	

}