package com.cuongnm.mercari.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cuongnm.mercari.model.Brands;
import com.cuongnm.mercari.model.Categories;
import com.cuongnm.mercari.model.Followers;
import com.cuongnm.mercari.model.News;
import com.cuongnm.mercari.model.Users;
import com.cuongnm.mercari.model.VerifyCodes;
import com.cuongnm.mercari.service.BrandService;
import com.cuongnm.mercari.service.CategoryService;
import com.cuongnm.mercari.service.JwtUserDetailsService;
import com.cuongnm.mercari.service.UtilService;
import com.cuongnm.mercari.utils.DefaultRequest;
import com.cuongnm.mercari.utils.JwtRequest;

@RestController
public class UserController {

	@Autowired
	private JwtUserDetailsService userDetailsService;
	
	@Autowired
	private CategoryService cService;

	@Autowired
	private BrandService bService;
	
	@Autowired
	private UtilService uService;
	
	
	@RequestMapping({"/changePassword"})
	public Users changePassword(@RequestHeader String Authorization, @RequestBody String password, @RequestBody String newPassword) {
		String username = userDetailsService.getUsernameFromToken(Authorization.substring(7));
		Users user = userDetailsService.getUserByUsername(username);
		if(user.getPassword().equals(password)) {
			return userDetailsService.updatePassword(user.getUsername(), newPassword);
		}
		return null;
	}

	@RequestMapping({ "/getCategory" })
	public List<Categories> getCategory(@RequestBody String categoryId) {
		return cService.loadCategoryByCategoryId(Long.parseLong(categoryId));
	}

	@RequestMapping({ "/createCodeResetPassword" })
	public String verifyCodeSending(@RequestBody String username) {
		return uService.createCodeVerify(username);
	}

	@RequestMapping({ "/checkCodeResetPassword" })
	public String verifyCodeChecking(@RequestBody String username, @RequestBody String verifyCode) {
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
	
	@RequestMapping({"/getListFollowed"})
	public ResponseEntity<?> getListFollowed(@RequestBody String userId, @RequestBody int index, @RequestBody int count, @RequestHeader String Authorization){
		String username = userDetailsService.getUsernameFromToken(Authorization.substring(7));
		Users user = userDetailsService.getUserByUsername(username);
		List<Followers> listFollower = uService.getFollowerUser(userId);
		for (Followers followers : listFollower) {
			if(followers.getUserId() == user.getUserId()) {
				// Todo here
			}
		}
		return null;
	}
	
	
	@RequestMapping({"/getListFollowing"})
	public ResponseEntity<?> getListFollowing(@RequestBody String userId, @RequestBody int index, @RequestBody int count, @RequestHeader String Authorization){
		String username = userDetailsService.getUsernameFromToken(Authorization.substring(7));
		Users user = userDetailsService.getUserByUsername(username);
		List<Followers> listFollower = uService.getFollowerOfUser(userId);
		for (Followers followers : listFollower) {
			if(followers.getUserId() == user.getUserId()) {
				// Todo here
			}
		}
		return null;
	}
	
	@RequestMapping({"/searchUser"})
	public String searchUser(@RequestBody DefaultRequest req) {
		System.out.println("1"+ req.getFirstName());
		List<Users> list = uService.searchUser(req.getFirstName());
		for (Users users : list) {
			System.out.println("2"+ users.getFirstName());
		}
		return null;
	}
	

}