package com.cuongnm.mercari.controller;

import java.util.List;
import java.util.Set;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cuongnm.mercari.exception.DataException;
import com.cuongnm.mercari.exception.UserInvalidException;
import com.cuongnm.mercari.model.Brands;
import com.cuongnm.mercari.model.Categories;
import com.cuongnm.mercari.model.JwtBlacklist;
import com.cuongnm.mercari.model.News;
import com.cuongnm.mercari.model.Products;
import com.cuongnm.mercari.model.Reactions;
import com.cuongnm.mercari.model.Sizes;
import com.cuongnm.mercari.model.Users;

import com.cuongnm.mercari.service.JwtUserDetailsService;
import com.cuongnm.mercari.service.UtilService;
import com.cuongnm.mercari.utils.CategoryRequest;
import com.cuongnm.mercari.utils.ChangePasswordRequest;
import com.cuongnm.mercari.utils.DefaultResponse;
import com.cuongnm.mercari.utils.ExceptionResponse;
import com.cuongnm.mercari.utils.ProductRequest;
import com.cuongnm.mercari.utils.RateRequest;

@RestController
public class UserController {

	@Autowired
	private JwtUserDetailsService userDetailsService;

	@Autowired
	private UtilService uService;

	@Autowired
	private PasswordEncoder bcryptEncoder;

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public ResponseEntity<?> createUser(@RequestBody Users user) {

		return ResponseEntity.ok(new DefaultResponse("1000", "OK",
				uService.addUser(user.getUsername(), bcryptEncoder.encode(user.getPassword()), user.getPhoneNumber())));
	}

	@RequestMapping(value = "/createCodeResetPassword", method = RequestMethod.GET)
	public ResponseEntity<?> createCodeResetPassword(@RequestParam String username) {
		try {
			return ResponseEntity.ok(new DefaultResponse("1000", "OK", uService.createCodeVerify(username)));
		} catch (UserInvalidException e1) {
			return new ResponseEntity<ExceptionResponse>(new ExceptionResponse("9995", "User is invalid"),
					HttpStatus.BAD_REQUEST);
		} catch (PersistenceException e2) {
			return new ResponseEntity<ExceptionResponse>(new ExceptionResponse("1001", "Can not connect to DB"),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<ExceptionResponse>(new ExceptionResponse("9999", "Exception error"),
					HttpStatus.BAD_REQUEST);
		}

	}

	@RequestMapping(value = "/checkCodeResetPassword", method = RequestMethod.GET)
	public ResponseEntity<?> checkCodeResetPassword(@RequestParam String username, @RequestParam String verifyCode) {
		Users user = userDetailsService.getUserByUsername(username);
		try {
			return ResponseEntity.ok(new DefaultResponse("1000", "OK", uService.compare(verifyCode, user)));
		} catch (UserInvalidException e) {
			return new ResponseEntity<ExceptionResponse>(new ExceptionResponse("9993", "Code verify is incorrect"),
					HttpStatus.BAD_REQUEST);
		} catch (PersistenceException e2) {
			return new ResponseEntity<ExceptionResponse>(new ExceptionResponse("1001", "Can not connect to DB"),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<ExceptionResponse>(new ExceptionResponse("9999", "Exception error"),
					HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/changePassword", method = RequestMethod.POST)
	public ResponseEntity<?> changePassword(@RequestHeader String Authorization,
			@RequestBody ChangePasswordRequest changePwdRequest) {
		try {
			String username = userDetailsService.getUsernameFromToken(Authorization.substring(7));
			Users users = userDetailsService.getUserByUsername(username);
			if (bcryptEncoder.matches(changePwdRequest.getOldPassword(), users.getPassword())) {
				uService.deleteVerifyCode(users);
				return ResponseEntity.ok(new DefaultResponse("1000", "OK",
						userDetailsService.updatePassword(users.getUsername(), changePwdRequest.getNewPassword())));
			}
			return new ResponseEntity<ExceptionResponse>(new ExceptionResponse("1017", "Fail to change password"),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<ExceptionResponse>(new ExceptionResponse("9999", "Exception error"),
					HttpStatus.BAD_REQUEST);
		}

	}

	@RequestMapping(value = "/createBrand", method = RequestMethod.POST)
	public ResponseEntity<?> createBrand(@RequestBody Brands brand) {
		return ResponseEntity.ok(new DefaultResponse("1000", "OK", uService.addBrand(brand)));
	}

	@RequestMapping(value = "/createSize", method = RequestMethod.POST)
	public ResponseEntity<?> createSize(@RequestBody Sizes sizes) {
		return ResponseEntity.ok(new DefaultResponse("1000", "OK", uService.addSize(sizes)));
	}

	@RequestMapping({ "/getAllNews" })
	public ResponseEntity<?> getAllNews() {
		List<News> results = null;
		try {
			results = uService.getAllNews();
		} catch (DataException e) {
			return new ResponseEntity<ExceptionResponse>(new ExceptionResponse("9999", "Exception error"),
					HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.ok(new DefaultResponse("1000", "OK", results));

	}

	@RequestMapping({ "/getNews" })
	public ResponseEntity<?> getNew(@RequestBody String id) {
		News news = null;
		try {
			news = uService.getNews(id);
		} catch (DataException e) {
			return new ResponseEntity<ExceptionResponse>(new ExceptionResponse("9999", "Exception error"),
					HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.ok(new DefaultResponse("1000", "OK", news));
	}

	@RequestMapping({ "/getBrands" })
	public ResponseEntity<?> getBrands() {
		List<Brands> result = null;
		try {
			result = uService.getAllBrand();
		} catch (DataException e) {
			return new ResponseEntity<ExceptionResponse>(new ExceptionResponse("9999", "Exception error"),
					HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.ok(new DefaultResponse("1000", "OK", result));

	}

	@RequestMapping(value = "/createCategory", method = RequestMethod.POST)
	public ResponseEntity<?> createCategory(@RequestBody CategoryRequest categoryInfo) {
		Categories result = null;
		try {
			result = uService
					.addCategory(new Categories(categoryInfo.getCategoryName(), categoryInfo.getParentCategoryId()));
		} catch (Exception e) {
			return new ResponseEntity<ExceptionResponse>(new ExceptionResponse("9999", "Exception error"),
					HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.ok(new DefaultResponse("1000", "OK", result));

	}

	@RequestMapping(value = "/getCategories", method = RequestMethod.GET)
	public ResponseEntity<?> getCategory(@RequestParam(value = "categoryId", required = false) String categoryId) {
		try {
			if (categoryId != null) {
				return ResponseEntity.ok(new DefaultResponse("1000", "OK",
						uService.loadCategoryByCategoryId(Long.parseLong(categoryId))));
			}
			return ResponseEntity.ok(new DefaultResponse("1000", "OK", uService.loadAllCategory()));

		} catch (NumberFormatException e) {
			return new ResponseEntity<ExceptionResponse>(new ExceptionResponse("1004", "Parameter is not valid"),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<ExceptionResponse>(new ExceptionResponse("9999", "Exception error"),
					HttpStatus.BAD_REQUEST);
		}

	}

	@RequestMapping(value = "/customLogout", method = RequestMethod.POST)
	public ResponseEntity<?> customLogout(@RequestBody String token) throws Exception {
		try {
			JwtBlacklist jwtBlacklist = new JwtBlacklist();
			jwtBlacklist.setToken(token);
			userDetailsService.saveJwtBlacklistToken(jwtBlacklist);
			return ResponseEntity.ok(new DefaultResponse("1000", "OK", null));
		} catch (Exception e) {
			return new ResponseEntity<ExceptionResponse>(new ExceptionResponse("9999", "Exception error"),
					HttpStatus.BAD_REQUEST);
		}

	}

	@RequestMapping(value = "/addProduct", method = RequestMethod.POST)
	public ResponseEntity<?> addProduct(@RequestBody ProductRequest productInfo) {
		Products product = new Products();
		product.setProductName(productInfo.getProductName());
		product.setPrice(productInfo.getPrice());
		product.setProductDescribed(productInfo.getProductDescribed());
		product.setProductImagePath(productInfo.getProductImagePath());
		product.setProductVideoPath(productInfo.getProductVideoPath());
		product.setState(productInfo.getState());
		product.setWeight(productInfo.getWeight());
		product.setAllowOffer(productInfo.isAllowOffer());
		product.setQuality(productInfo.getQuality());
		try {
			product = uService.addProduct(product);
			return ResponseEntity.ok(new DefaultResponse("1000", "OK", product));
		} catch (Exception e) {
			return new ResponseEntity<ExceptionResponse>(new ExceptionResponse("9999", "Exception error"),
					HttpStatus.BAD_REQUEST);
		}

	}

	@RequestMapping({ "/getListFollowed" })
	public ResponseEntity<?> getListFollowed(@RequestParam String userId, @RequestParam int index, @RequestParam int count,
			@RequestHeader String Authorization) {
		if(Authorization == null) {
			return ResponseEntity.ok(new DefaultResponse("1000", "OK", null));
		}
		return new ResponseEntity<ExceptionResponse>(new ExceptionResponse("9999", "Exception error"),
				HttpStatus.BAD_REQUEST);
	}

//	@RequestMapping({ "/getListFollowing" })
//	public List<Followers> getListFollowing(@RequestBody String userId, @RequestBody int index, @RequestBody int count,
//			@RequestHeader String Authorization) {
//		String username = userDetailsService.getUsernameFromToken(Authorization.substring(7));
//		Users user = userDetailsService.getUserByUsername(username);
//		List<Followers> listFollower = uService.getFollowerOfUser(userId);
//		for (Followers followers : listFollower) {
//			
//		}
//		return null;
//	}

//	@RequestMapping({ "/searchUser" })
//	public String searchUser(@RequestBody DefaultRequest req) {
//		List<Users> list = uService.searchUser(req.getFirstName());
//		for (Users users : list) {
//			System.out.println("2" + users.getFirstName());
//		}
//		return null;
//	}

	@RequestMapping({ "/getListBlock" })
	public ResponseEntity<?> getListBlock(@RequestHeader String Authorization) {
		String username = userDetailsService.getUsernameFromToken(Authorization.substring(7));
		Users user = userDetailsService.getUserByUsername(username);
		List<Reactions> result = null;
		try {
			result = uService.getListBlock(user.getUserId());
			return ResponseEntity.ok(new DefaultResponse("1000", "OK", result));
		} catch (Exception e) {
			return new ResponseEntity<ExceptionResponse>(new ExceptionResponse("9999", "Exception error"),
					HttpStatus.BAD_REQUEST);
		}

	}

	@RequestMapping({ "/getListLike" })
	public ResponseEntity<?> getListLike(@RequestHeader String Authorization) {
		String username = userDetailsService.getUsernameFromToken(Authorization.substring(7));
		Users user = userDetailsService.getUserByUsername(username);
		List<Reactions> result = null;
		try {
			result = uService.getListLike(user.getUserId());
			return ResponseEntity.ok(new DefaultResponse("1000", "OK", result));
		} catch (Exception e) {
			return new ResponseEntity<ExceptionResponse>(new ExceptionResponse("9999", "Exception error"),
					HttpStatus.BAD_REQUEST);
		}

	}

	@RequestMapping({ "/getListRate" })
	public ResponseEntity<?> getListRate(@RequestHeader String Authorization, @RequestBody RateRequest rateRequest) {
		String username = userDetailsService.getUsernameFromToken(Authorization.substring(7));
		Users user = userDetailsService.getUserByUsername(username);

		List<Reactions> result = null;
		try {
			result = uService.getListRate(user.getUserId(), rateRequest.getTargetUserId(), rateRequest.getLevel());
			return ResponseEntity.ok(new DefaultResponse("1000", "OK", result));
		} catch (Exception e) {
			return new ResponseEntity<ExceptionResponse>(new ExceptionResponse("9999", "Exception error"),
					HttpStatus.BAD_REQUEST);
		}

	}

}