package com.cuongnm.mercari.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cuongnm.mercari.exception.DataException;
import com.cuongnm.mercari.model.Brands;
import com.cuongnm.mercari.model.Campaigns;
import com.cuongnm.mercari.model.Categories;
import com.cuongnm.mercari.model.Followers;

import com.cuongnm.mercari.model.News;
import com.cuongnm.mercari.model.Orders;
import com.cuongnm.mercari.model.Products;
import com.cuongnm.mercari.model.Reactions;
import com.cuongnm.mercari.model.SearchConditions;
import com.cuongnm.mercari.model.Users;

import com.cuongnm.mercari.service.JwtUserDetailsService;
import com.cuongnm.mercari.service.UtilService;
import com.cuongnm.mercari.utils.BuyRequest;
import com.cuongnm.mercari.utils.CampaignRequest;
import com.cuongnm.mercari.utils.CategoryRequest;
import com.cuongnm.mercari.utils.DefaultResponse;
import com.cuongnm.mercari.utils.ExceptionResponse;
import com.cuongnm.mercari.utils.NewsRequest;
import com.cuongnm.mercari.utils.ProductRequest;
import com.cuongnm.mercari.utils.RateRequest;
import com.cuongnm.mercari.utils.SearchRequest;
import com.cuongnm.mercari.utils.UpdateUserRequest;

@RestController
public class UserController {

	@Autowired
	private JwtUserDetailsService userDetailsService;

	@Autowired
	private UtilService uService;

	@Autowired
	private PasswordEncoder bcryptEncoder;

	// Start of user request
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public ResponseEntity<?> createUser(@RequestBody Users user) {
		return ResponseEntity.ok(new DefaultResponse("1000", "OK",
				uService.addUser(user.getUsername(), bcryptEncoder.encode(user.getPassword()), user.getPhoneNumber())));
	}

	@RequestMapping(value = "/getInfo", method = RequestMethod.GET)
	public ResponseEntity<?> getInfoUser(@RequestHeader(value = "Authorization", required = false) String Authorization,
			@RequestParam(value = "id", required = false) String id) {
		Users user = null;
		try {
			if (Authorization == null) {
				user = uService.getUserInfo(Long.parseLong(id));
				user.setPassword("");
			} else {
				String username = userDetailsService.getUsernameFromToken(Authorization.substring(7));
				user = userDetailsService.getUserByUsername(username);
				user.setPassword("");
			}
			return ResponseEntity.ok(new DefaultResponse("1000", "OK", user));
		} catch (Exception e) {
			return new ResponseEntity<ExceptionResponse>(new ExceptionResponse("9999", "Exception error"),
					HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/updateUserInfo", method = RequestMethod.PUT)
	public ResponseEntity<?> updateUserInfo(@RequestHeader String Authorization, @RequestParam String id,
			@RequestBody UpdateUserRequest userRequest) {
		Users user = null;
		try {
			String username = userDetailsService.getUsernameFromToken(Authorization.substring(7));
			if (userDetailsService.getUserByUsername(username).getUserId().equals(Long.parseLong(id))) {
				user = uService.updateUser(userRequest, Long.parseLong(id));
				return ResponseEntity.ok(new DefaultResponse("1000", "OK", user));
			}
			return new ResponseEntity<ExceptionResponse>(new ExceptionResponse("9991", "No authorize to update user"),
					HttpStatus.BAD_REQUEST);
		} catch (NumberFormatException e) {
			return new ResponseEntity<ExceptionResponse>(new ExceptionResponse("9996", "Number format error"),
					HttpStatus.BAD_REQUEST);
		} catch (DataException e) {
			return new ResponseEntity<ExceptionResponse>(new ExceptionResponse("9999", "Exception error"),
					HttpStatus.BAD_REQUEST);
		} catch (EntityNotFoundException e) {
			return new ResponseEntity<ExceptionResponse>(new ExceptionResponse("9994", "No Data or end of list data"),
					HttpStatus.BAD_REQUEST);
		}

	}

	@RequestMapping(value = "/getUserFollowers", method = RequestMethod.GET)
	public ResponseEntity<?> getUserFollowers(@RequestParam String targetId) {
		try {
			List<Users> listFollower = uService.getUserFollowers(Long.parseLong(targetId));
			return ResponseEntity.ok(new DefaultResponse("1000", "OK", listFollower));
		} catch (NumberFormatException e) {
			return new ResponseEntity<ExceptionResponse>(new ExceptionResponse("9999", "Exception error"),
					HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/getProductFollowers", method = RequestMethod.GET)
	public ResponseEntity<?> getProductFollowers(@RequestParam String targetId) {
		try {
			List<Users> listFollower = uService.getProductFollowers(Long.parseLong(targetId));
			return ResponseEntity.ok(new DefaultResponse("1000", "OK", listFollower));
		} catch (NumberFormatException e) {
			return new ResponseEntity<ExceptionResponse>(new ExceptionResponse("9999", "Exception error"),
					HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/followUser", method = RequestMethod.POST)
	public ResponseEntity<?> followUser(@RequestHeader String Authorization, @RequestParam String userId) {
		try {
			String username = userDetailsService.getUsernameFromToken(Authorization.substring(7));
			Users user = userDetailsService.getUserByUsername(username);
			Followers follower = uService.addFollower(user, Long.parseLong(userId), 0);
			return ResponseEntity.ok(new DefaultResponse("1000", "OK", follower));
		} catch (Exception e) {
			return new ResponseEntity<ExceptionResponse>(new ExceptionResponse("9999", "Exception error"),
					HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/followProduct", method = RequestMethod.POST)
	public ResponseEntity<?> followProduct(@RequestHeader String Authorization, @RequestParam String productId) {
		try {
			String username = userDetailsService.getUsernameFromToken(Authorization.substring(7));
			Users user = userDetailsService.getUserByUsername(username);
			Followers follower = uService.addFollower(user, Long.parseLong(productId), 1);
			return ResponseEntity.ok(new DefaultResponse("1000", "OK", follower));
		} catch (Exception e) {
			return new ResponseEntity<ExceptionResponse>(new ExceptionResponse("9999", "Exception error"),
					HttpStatus.BAD_REQUEST);
		}
	}

	// End of user request

	// Start of brand request
	// =====================================================================

	@RequestMapping(value = "/createBrand", method = RequestMethod.POST)
	@PreAuthorize("@appAuthorizer.authorize(authentication, this)")
	public ResponseEntity<?> createBrand(@RequestBody Brands brand) {
		return ResponseEntity.ok(new DefaultResponse("1000", "OK", uService.addBrand(brand)));
	}

	@RequestMapping(value = "/getBrands", method = RequestMethod.GET)
	public ResponseEntity<?> getBrands(@RequestParam int index, @RequestParam int count) {
		List<Brands> result = null;
		try {
			result = uService.getAllBrand(index, count);
		} catch (DataException e) {
			return new ResponseEntity<ExceptionResponse>(new ExceptionResponse("9999", "Exception error"),
					HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.ok(new DefaultResponse("1000", "OK", result));
	}

	@RequestMapping(value = "/updateBrand", method = RequestMethod.PUT)
	@PreAuthorize("@appAuthorizer.authorize(authentication, this)")
	public ResponseEntity<?> updateBrands(@RequestParam String brandId, @RequestBody Brands brand) {
		try {
			return ResponseEntity
					.ok(new DefaultResponse("1000", "OK", uService.updateBrand(brand, Long.parseLong(brandId))));
		} catch (NumberFormatException e) {
			return new ResponseEntity<ExceptionResponse>(new ExceptionResponse("9999", "Exception error"),
					HttpStatus.BAD_REQUEST);
		} catch (DataException e) {
			return new ResponseEntity<ExceptionResponse>(new ExceptionResponse("9999", "Exception error"),
					HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/deleteBrand", method = RequestMethod.DELETE)
	@PreAuthorize("@appAuthorizer.authorize(authentication, this)")
	public ResponseEntity<?> deleteBrands(@RequestParam String brandId) {
		try {
			uService.deleteBrand(Long.parseLong(brandId));
			return ResponseEntity.ok(new DefaultResponse("1000", "OK", null));
		} catch (NumberFormatException e) {
			return new ResponseEntity<ExceptionResponse>(new ExceptionResponse("9999", "Exception error"),
					HttpStatus.BAD_REQUEST);
		} catch (DataException e) {
			return new ResponseEntity<ExceptionResponse>(new ExceptionResponse("9999", "Exception error"),
					HttpStatus.BAD_REQUEST);
		}
	}

	// End of brand request

	// Start of category request
	// ================================================================

	@RequestMapping(value = "/createCategory", method = RequestMethod.POST)
	@PreAuthorize("@appAuthorizer.authorize(authentication, this)")
	public ResponseEntity<?> createCategory(@RequestBody CategoryRequest categoryInfo) {
		Categories result = null;
		try {
			result = uService.addCategory(new Categories(categoryInfo.getCategoryName()),
					categoryInfo.getParentCategoryId());
		} catch (Exception e) {
			return new ResponseEntity<ExceptionResponse>(new ExceptionResponse("9999", "Exception error"),
					HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.ok(new DefaultResponse("1000", "OK", result));
	}

	@RequestMapping(value = "/getCategories", method = RequestMethod.GET)
	public ResponseEntity<?> getCategory(@RequestParam(value = "categoryId", required = false) String categoryId,
			@RequestParam int index, @RequestParam int count) {
		try {
			if (categoryId != null) {
				return ResponseEntity.ok(new DefaultResponse("1000", "OK",
						uService.loadCategoryByCategoryId(Long.parseLong(categoryId))));
			}
			return ResponseEntity.ok(new DefaultResponse("1000", "OK", uService.loadAllCategory(index, count)));

		} catch (NumberFormatException e) {
			return new ResponseEntity<ExceptionResponse>(new ExceptionResponse("1004", "Parameter is not valid"),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<ExceptionResponse>(new ExceptionResponse("9999", "Exception error"),
					HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/updateCategory", method = RequestMethod.PUT)
	@PreAuthorize("@appAuthorizer.authorize(authentication, this)")
	public ResponseEntity<?> updateCategory(@RequestParam String categoryId, @RequestBody Categories categoryInfo) {
		Categories result = null;
		try {
			result = uService.updateCategory(Long.parseLong(categoryId), categoryInfo);
		} catch (Exception e) {
			return new ResponseEntity<ExceptionResponse>(new ExceptionResponse("9999", "Exception error"),
					HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.ok(new DefaultResponse("1000", "OK", result));
	}

	@RequestMapping(value = "/deleteCategory", method = RequestMethod.DELETE)
	@PreAuthorize("@appAuthorizer.authorize(authentication, this)")
	public ResponseEntity<?> deleteCategory(@RequestParam String categoryId) {
		try {
			uService.deleteCategory(Long.parseLong(categoryId));
			return ResponseEntity.ok(new DefaultResponse("1000", "OK", null));
		} catch (NumberFormatException e) {
			return new ResponseEntity<ExceptionResponse>(new ExceptionResponse("9999", "Exception error"),
					HttpStatus.BAD_REQUEST);
		} catch (DataException e) {
			return new ResponseEntity<ExceptionResponse>(new ExceptionResponse("9999", "Exception error"),
					HttpStatus.BAD_REQUEST);
		}
	}

	// End of category request

	// Start of product request
	// ================================================================
	@RequestMapping(value = "/createProduct", method = RequestMethod.POST)
	public ResponseEntity<?> createProduct(@RequestBody ProductRequest productInfo) {
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
		product.setBrands(uService.getBrand(productInfo.getBrandId()));
		List<Categories> categories = uService.getCategories(productInfo.getCategoryId());
		product.setCategories(categories);
		try {
			product = uService.addProduct(product);
			return ResponseEntity.ok(new DefaultResponse("1000", "OK", product));
		} catch (Exception e) {
			return new ResponseEntity<ExceptionResponse>(new ExceptionResponse("9999", "Exception error"),
					HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/buyProduct", method = RequestMethod.POST)
	public ResponseEntity<?> buyProduct(@RequestHeader String Authorization, @RequestBody BuyRequest buyRequest) {
		String username = userDetailsService.getUsernameFromToken(Authorization.substring(7));
		Users user = userDetailsService.getUserByUsername(username);
		Map<Long, Integer> setProduct = buyRequest.getSetProducts();
		Set<Long> productIds = setProduct.keySet();
		Set<Products> products = uService.getProductByIds(productIds);
		Long amount = 0L;
		for (Products product : products) {
			amount += product.getPrice() * setProduct.get(product.getProductId());
		}
		Orders orders = new Orders();
		orders.setPayType(buyRequest.getPayType());
		orders.setAddress(buyRequest.getAddress());
		orders.setCity(buyRequest.getCity());
		orders.setStatus(buyRequest.getStatus());
		orders.setCreatedDate();
		orders.setProducts(new ArrayList<>(products));
		orders.setAmount(amount);
		orders.setUsers(user);

		try {
			orders = uService.addOrder(orders);
			return ResponseEntity.ok(new DefaultResponse("1000", "OK", orders));
		} catch (Exception e) {
			return new ResponseEntity<ExceptionResponse>(new ExceptionResponse("9999", "Exception error"),
					HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/getProduct", method = RequestMethod.GET)
	public ResponseEntity<?> getProduct(@RequestParam String productId) {
		try {
			Map<Products, List<Users>> result = uService.getProduct(Long.parseLong(productId));
			return ResponseEntity.ok(new DefaultResponse("1000", "OK", result));
		} catch (Exception e) {
			return new ResponseEntity<ExceptionResponse>(new ExceptionResponse("9999", "Exception error"),
					HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/updateProduct", method = RequestMethod.PUT)
	public ResponseEntity<?> updateProduct(@RequestHeader String Authorization, @RequestParam String productId,
			@RequestBody ProductRequest productInfo) {
		try {
			String username = userDetailsService.getUsernameFromToken(Authorization.substring(7));
			Long createdUser = uService.getProductById(Long.parseLong(productId)).getCreatedUser();
			if (userDetailsService.getUserByUsername(username).getUserId().equals(createdUser)) {
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
				product.setBrands(uService.getBrand(productInfo.getBrandId()));
				List<Categories> categories = uService.getCategories(productInfo.getCategoryId());
				product.setCategories(categories);
				Products result = uService.updateProduct(Long.parseLong(productId), product);
				return ResponseEntity.ok(new DefaultResponse("1000", "OK", result));
			}
			return new ResponseEntity<ExceptionResponse>(
					new ExceptionResponse("9990", "No authorize to update product"), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<ExceptionResponse>(new ExceptionResponse("9999", "Exception error"),
					HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public ResponseEntity<?> searchProduct(@RequestBody SearchRequest searchRequest) {
		try {
			List<Products> results = null;
			if (searchRequest.getKeyword() == null) {
				results = uService.filter(searchRequest.getCategoryId(), searchRequest.getBrandId(),
						searchRequest.getPriceMin(), searchRequest.getPriceMax());
			} else {
				results = uService.searchByKeyword(searchRequest.getKeyword());
			}
			return ResponseEntity.ok(new DefaultResponse("1000", "OK", results));
		} catch (Exception e) {
			return new ResponseEntity<ExceptionResponse>(new ExceptionResponse("9999", "Exception error"),
					HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/saveSearchConditions", method = RequestMethod.POST)
	public ResponseEntity<?> saveSearchConditions(@RequestHeader String Authorization,
			@RequestBody SearchRequest searchRequest) {
		try {
			String username = userDetailsService.getUsernameFromToken(Authorization.substring(7));
			Users user = userDetailsService.getUserByUsername(username);
			SearchConditions searchConditions = uService.addSearchCondition(new SearchConditions(
					searchRequest.getKeyword(), searchRequest.getPriceMin(), searchRequest.getPriceMax(), user));
			return ResponseEntity.ok(new DefaultResponse("1000", "OK", searchConditions));
		} catch (Exception e) {
			return new ResponseEntity<ExceptionResponse>(new ExceptionResponse("9999", "Exception error"),
					HttpStatus.BAD_REQUEST);
		}
	}

	// End of product request

	// Start of news request
	@RequestMapping({ "/getAllNews" })
	public ResponseEntity<?> getAllNews(@RequestParam int index, @RequestParam int count) {
		List<News> results = null;
		try {
			results = uService.getAllNews(index, count);
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

	@RequestMapping({ "/addNews" })
	public ResponseEntity<?> addNews(@RequestHeader String Authorization, @RequestBody NewsRequest news) {
		try {
			String username = userDetailsService.getUsernameFromToken(Authorization.substring(7));
			Users user = userDetailsService.getUserByUsername(username);
			News newsInfo = new News();
			newsInfo.setTitle(news.getTitle());
			newsInfo.setContent(news.getContent());
			newsInfo.setUsers(user);
			newsInfo.setCreatedDate();
			return ResponseEntity.ok(new DefaultResponse("1000", "OK", uService.addNews(newsInfo)));
		} catch (Exception e) {
			return new ResponseEntity<ExceptionResponse>(new ExceptionResponse("9999", "Exception error"),
					HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping({ "/getListFollowed" })
	public ResponseEntity<?> getListFollowed(@RequestParam String userId, @RequestParam int index,
			@RequestParam int count, @RequestHeader String Authorization) {
		if (Authorization == null) {
			return ResponseEntity.ok(new DefaultResponse("1000", "OK", null));
		}
		return new ResponseEntity<ExceptionResponse>(new ExceptionResponse("9999", "Exception error"),
				HttpStatus.BAD_REQUEST);
	}

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

	@RequestMapping({ "/createCampaign" })
	@PreAuthorize("@appAuthorizer.authorize(authentication, this)")
	public ResponseEntity<?> createCampaign(@RequestBody CampaignRequest campaignRequest) {
		try {
			Campaigns result = uService.createCampaign(new Campaigns(campaignRequest.getCampaignsName(),
					campaignRequest.getCampaignImagePath(), campaignRequest.getCampaignDescribed()));
			return ResponseEntity.ok(new DefaultResponse("1000", "OK", result));
		} catch (Exception e) {
			return new ResponseEntity<ExceptionResponse>(new ExceptionResponse("9999", "Exception error"),
					HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping({ "/getCampaign" })
	@PreAuthorize("@appAuthorizer.authorize(authentication, this)")
	public ResponseEntity<?> getCampaign(@RequestParam(value = "campaignId", required = false) String campaignId) {
		try {
			if (campaignId == null) {
				List<Campaigns> list = uService.getCampaigns();
				return ResponseEntity.ok(new DefaultResponse("1000", "OK", list));
			} else {
				Campaigns campaigns = uService.getCampaign(Long.parseLong(campaignId));
				return ResponseEntity.ok(new DefaultResponse("1000", "OK", campaigns));
			}
		} catch (Exception e) {
			return new ResponseEntity<ExceptionResponse>(new ExceptionResponse("9999", "Exception error"),
					HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping({ "/likeUser" })
	public ResponseEntity<?> likeUser(@RequestHeader String Authorization, @RequestParam String userId) {
		try {
			String username = userDetailsService.getUsernameFromToken(Authorization.substring(7));
			Users user = userDetailsService.getUserByUsername(username);
			Reactions like = new Reactions();
			like.setLike(true);
			like.setReactionUser(user.getUserId());
			like.setReactionType(1);
			like.setUsers(uService.getUserInfo(Long.parseLong(userId)));
			return ResponseEntity.ok(new DefaultResponse("1000", "OK", uService.likeUser(like)));
		} catch (Exception e) {
			return new ResponseEntity<ExceptionResponse>(new ExceptionResponse("9999", "Exception error"),
					HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping({ "/likeProduct" })
	public ResponseEntity<?> likeProduct(@RequestHeader String Authorization, @RequestParam String productId) {
		try {
			String username = userDetailsService.getUsernameFromToken(Authorization.substring(7));
			Users userReaction = userDetailsService.getUserByUsername(username);
			Reactions like = new Reactions();
			like.setLike(true);
			like.setReactionUser(userReaction.getUserId());
			like.setReactionType(1);
			like.setProducts(uService.getProductById(Long.parseLong(productId)));
			return ResponseEntity.ok(new DefaultResponse("1000", "OK", uService.likeUser(like)));
		} catch (Exception e) {
			return new ResponseEntity<ExceptionResponse>(new ExceptionResponse("9999", "Exception error"),
					HttpStatus.BAD_REQUEST);
		}
	}

}