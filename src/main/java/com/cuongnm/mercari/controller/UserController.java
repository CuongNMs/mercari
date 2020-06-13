package com.cuongnm.mercari.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cuongnm.mercari.model.Brands;
import com.cuongnm.mercari.model.Categories;
import com.cuongnm.mercari.model.News;
import com.cuongnm.mercari.model.VerifyCodes;
import com.cuongnm.mercari.repository.BrandRepository;
import com.cuongnm.mercari.service.BrandService;
import com.cuongnm.mercari.service.CategoryService;
import com.cuongnm.mercari.service.UtilService;

@RestController
public class UserController {

	@Autowired
	private CategoryService cService;

	@Autowired
	private BrandService bService;
	
	@Autowired
	private UtilService uService;

	@RequestMapping({ "/getCategory" })
	public List<Categories> getCategory(@RequestBody String categoryId) {
		return cService.loadCategoryByCategoryId(Long.parseLong(categoryId));
	}

	@RequestMapping({ "/create-code-reset-password" })
	public String verifyCodeSending(@RequestBody String username) {
		return uService.createCodeVerify(username);
	}

	@RequestMapping({ "/check-code-reset-password" })
	public String verifyCodeChecking(@RequestBody String username, String verifyCode) {
		if (uService.compare(new VerifyCodes(verifyCode, username))) {
			return "SUCCESS";
		}
		return "FAILED";
	}

	@RequestMapping({ "/getAllNews" })
	public List<News> getAllNews() {
		return uService.getAllNews();
	}

	@RequestMapping({ "/getNews" })
	public News getNew(@RequestBody String id) {
		return uService.getNews(id);
	}
	
	@RequestMapping({ "/getBrands" })
	public List<Brands> getNew() {
		return bService.getAllBrand();
	}

}