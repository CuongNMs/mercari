package com.cuongnm.mercari.service;

import java.util.HashMap;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

import org.springframework.stereotype.Service;

import com.cuongnm.mercari.exception.DataException;
import com.cuongnm.mercari.exception.UserInvalidException;
import com.cuongnm.mercari.model.Brands;
import com.cuongnm.mercari.model.Campaigns;
import com.cuongnm.mercari.model.Categories;
import com.cuongnm.mercari.model.Followers;
import com.cuongnm.mercari.model.News;
import com.cuongnm.mercari.model.Orders;
import com.cuongnm.mercari.model.Products;
import com.cuongnm.mercari.model.Reactions;
import com.cuongnm.mercari.model.SearchConditions;
import com.cuongnm.mercari.model.Sizes;
import com.cuongnm.mercari.model.Users;
import com.cuongnm.mercari.model.VerifyCodes;
import com.cuongnm.mercari.repository.BrandRepository;
import com.cuongnm.mercari.repository.CampaignRepository;
import com.cuongnm.mercari.repository.CategoryRepository;
import com.cuongnm.mercari.repository.FollowerRepository;
import com.cuongnm.mercari.repository.NewsRepository;
import com.cuongnm.mercari.repository.OrderRepository;
import com.cuongnm.mercari.repository.ProductRepository;
import com.cuongnm.mercari.repository.ReactionRepository;
import com.cuongnm.mercari.repository.SearchConditionRepository;
import com.cuongnm.mercari.repository.SizeRepository;
import com.cuongnm.mercari.repository.UserRepository;
import com.cuongnm.mercari.repository.VerifyCodeRepository;
import com.cuongnm.mercari.utils.UpdateUserRequest;

@Service
public class UtilService {

	@Autowired
	private BrandRepository bRepository;

	@Autowired
	public VerifyCodeRepository vRepository;

	@Autowired
	public NewsRepository nRepository;

	@Autowired
	public FollowerRepository fRepository;

	@Autowired
	public UserRepository uRepository;

	@Autowired
	public ReactionRepository rRepository;

	@Autowired
	private CategoryRepository cRepository;

	@Autowired
	private SizeRepository sRepository;

	@Autowired
	private ProductRepository pRepository;

	@Autowired
	private OrderRepository oRepository;

	@Autowired
	private CampaignRepository caRepository;

	@Autowired
	private SearchConditionRepository sdRepository;

	public String createCodeVerify(String username) throws UserInvalidException {
		String code = null;
		try {
			Users user = uRepository.findByUsername(username);
			if (user == null)
				throw new UserInvalidException("User is invalid");
			code = (int) Math.floor(((Math.random() * 899999) + 100000)) + "";
			VerifyCodes saveObj = new VerifyCodes();
			saveObj.setVerifyCode(code);
			saveObj.setUsers(user);
			vRepository.save(saveObj);
		} catch (UserInvalidException e1) {
			throw new UserInvalidException("User is invalid");
		}
		return code;
	}

	public String getCodeVerify(String username) {
		try {
			Users user = uRepository.findByUsername(username);
			if (user == null) {
				return null;
			}
			return vRepository.getVerifyCodeByUserId(user.getUserId());
		} catch (Exception e) {
			return null;
		}
	}

	public Orders addOrder(Orders order) throws IllegalArgumentException {
		Orders orders = oRepository.save(order);
		if (orders == null) {
			throw new IllegalArgumentException();
		}
		return orders;
	}

	public Users getUserInfo(Long userId) throws IllegalArgumentException {
		return uRepository.getUserInfo(userId);
	}

	public String compare(String verifyCodes, Users user) throws UserInvalidException {
		VerifyCodes tmp = vRepository.findByVerifyCode(verifyCodes);
		if (tmp == null || user == null) {
			throw new UserInvalidException("User is invalid");
		}
		if (user.getUserId() == tmp.getUsers().getUserId()) {
			return "VALID";
		}
		return "INVALID";
	}

	public Products addProduct(Products product) throws IllegalArgumentException {
		Products result = pRepository.save(product);
		if (result == null) {
			throw new IllegalArgumentException();
		}
		return result;

	}

	public Products updateProduct(Long productId, Products productInfo) throws DataException {
		Optional<Products> productFound = pRepository.findById(productId);
		if (productFound.isPresent()) {
			Products product = productFound.get();
			product.setProductName(productInfo.getProductName());
			product.setProductDescribed(productInfo.getProductDescribed());
			product.setProductImagePath(productInfo.getProductImagePath());
			product.setProductVideoPath(productInfo.getProductVideoPath());
			product.setCategories(productInfo.getCategories());
			product.setBrands(productInfo.getBrands());
			pRepository.save(product);
			return product;
		}
		return null;
	}

	public Set<Products> getProductByIds(Set<Long> productIds) {
		return pRepository.findByIds(productIds);
	}

	public Products getProductById(Long productId) {
		return pRepository.getOne(productId);
	}

	public void deleteProduct(String id) {
		pRepository.deleteById(Long.parseLong(id));
	}

	public Brands addBrand(Brands brand) {
		return bRepository.save(brand);
	}

	public Brands getBrand(Long brandId) {
		return bRepository.getOne(brandId);
	}

	public Categories getCategory(Long categoryId) {
		return cRepository.getOne(categoryId);
	}

	public List<Categories> getCategories(List<Long> categoryIds) {
		return cRepository.findAllById(categoryIds);
	}

	public Users addUser(String username, String password, String phoneNumber) {
		Users user = new Users();
		user.setUsername(username);
		user.setPassword(password);
		user.setPhoneNumber(phoneNumber);
		return uRepository.save(user);
	}

	public void deleteVerifyCode(Users users) {
		vRepository.deleteByUsers(users);
	}

	public List<Brands> getAllBrand(int index, int count) throws DataException {
		List<Brands> result = bRepository.findAll(PageRequest.of(index, count)).getContent();
		if (result == null) {
			throw new DataException("No Data or end of list data");
		}
		return result;
	}

	public List<News> getAllNews(int index, int count) throws DataException {
		List<News> results = nRepository.findAll(PageRequest.of(index, count)).getContent();
		if (results == null) {
			throw new DataException("No Data or end of list data");
		}
		return results;
	}

	public News getNews(String id) throws DataException {
		News news = nRepository.getOne(Long.parseLong(id));
		if (news == null) {
			throw new DataException("No Data or end of list data");
		}
		return news;
	}

	public News createNews(News newsInfo) throws DataException {
		News news = nRepository.save(newsInfo);
		if (news == null) {
			throw new IllegalArgumentException();
		}
		return news;
	}

	public List<Categories> loadCategoryByCategoryId(Long categoryId) {
		return cRepository.findByCategoryId(categoryId);
	}

	public List<Categories> loadAllCategory(int index, int count) throws DataException {
		List<Categories> results = cRepository.findAll(PageRequest.of(index, count)).getContent();
		if (results == null) {
			throw new DataException("No Data or end of list data");
		}
		return results;
	}

	public Categories addCategory(Categories categories, Long parentCategoryId) {
		if (parentCategoryId != null) {
			Categories parentCategory = cRepository.getOne(parentCategoryId);
			categories.setParentCategory(parentCategory);
		}
		Categories result = cRepository.save(categories);
		if (result == null) {
			throw new IllegalArgumentException();
		}
		return result;
	}

	public List<Users> getProductFollowers(Long productId) {
		return uRepository.findProductFollowers(productId);
	}

	public List<Users> getUserFollowers(Long userId) {
		return uRepository.findUserFollowers(userId);
	}

	public Followers addFollower(Users user, Long id, int type) {
		Followers followers = new Followers();
		if (type == 1) {
			followers.setFollowProductTarget(id);
		} else {
			followers.setFollowUserTarget(id);
		}
		followers.setUsers(user);
		return fRepository.save(followers);
	}

	public List<Reactions> getListBlock(Long userId) throws DataException {
		List<Reactions> result = rRepository.getListBlock(userId);
		if (result == null) {
			throw new DataException("No Data or end of list data");
		}
		return result;
	}

	public List<Reactions> getListLike(Long userId) {
		return rRepository.getListLike(userId);
	}

	public List<Reactions> getListRate(Long userId, Long targetUserId, Integer level) {
		if (targetUserId == null) {
			if (level == null) {
				return rRepository.getAllUserListRate(userId);
			}
			return rRepository.getAllUserListRateByLevel(userId, level);
		} else {
			if (level == null) {
				return rRepository.getAllListRateOfUser(targetUserId);
			}
			return rRepository.getAllListRateOfUserByLevel(targetUserId, level);
		}
	}

	public Reactions likeUser(Reactions reaction) {
		return rRepository.save(reaction);
	}

	public Sizes addSize(Sizes sizes) {
		return sRepository.save(sizes);
	}

	public Users updateUser(UpdateUserRequest userInfo, Long id) throws DataException, EntityNotFoundException {
		Optional<Users> userFound = uRepository.findById(id);
		if (userFound.isPresent()) {
			Users user = userFound.get();
			user.setAddress(userInfo.getAddress());
			user.setAvatarPath(userInfo.getAvatarPath());
			user.setBackgroundImagePath(userInfo.getBackgroundImagePath());
			user.setBalance(userInfo.getBalance());
			user.setCity(userInfo.getCity());
			user.setEmail(userInfo.getEmail());
			user.setFirstName(userInfo.getFirstName());
			user.setLastName(userInfo.getLastName());
			user.setPhoneNumber(userInfo.getPhoneNumber());
			user.setStatus(userInfo.getStatus());
			uRepository.save(user);
			return user;
		}
		return null;

	}

	public Brands updateBrand(Brands brandInfo, long id) throws DataException {
		Optional<Brands> brandFound = bRepository.findById(id);
		if (brandFound.isPresent()) {
			Brands brand = brandFound.get();
			brand.setBrandName(brandInfo.getBrandName());
			bRepository.save(brand);
			return brand;
		}
		return null;
	}

	public Categories updateCategory(Long id, Categories categoryInfo) throws DataException {

		Optional<Categories> categoryFound = cRepository.findById(id);
		if (categoryFound.isPresent()) {
			Categories category = categoryFound.get();
			category.setCategoryName(categoryInfo.getCategoryName());
			cRepository.save(category);
			return category;
		}

		return null;
	}

	public void deleteBrand(long brandId) throws DataException {
		try {
			bRepository.deleteById(brandId);
		} catch (Exception e) {
			throw new DataException("Delete failed");
		}
	}

	public void deleteCategory(long categoryId) throws DataException {
		try {
			cRepository.deleteById(categoryId);
		} catch (Exception e) {
			throw new DataException("Delete failed");
		}
	}

	public Campaigns createCampaign(Campaigns campaigns) throws DataException {
		return caRepository.save(campaigns);
	}

	public News addNews(News news) {
		return nRepository.save(news);
	}

	public Map<Products, List<Users>> getProduct(Long productId) {
		Products products = pRepository.getOne(productId);
		List<Users> listFollower = getProductFollowers(productId);
		Map<Products, List<Users>> result = new HashMap<Products, List<Users>>();
		result.put(products, listFollower);
		return result;

	}

	public List<Products> filter(List<Long> categoryId, List<Long> brandId, Long priceMin, Long priceMax) {
		List<Products> results = pRepository.filter(categoryId, brandId, priceMin, priceMax);
		return results;
	}

	public List<Products> searchByKeyword(String keyword) {
		List<Products> results = pRepository.searchByKeyword(keyword);
		return results;
	}

	public List<Campaigns> getCampaigns() {
		return caRepository.findAll();
	}

	public Campaigns getCampaign(long id) {
		return caRepository.getOne(id);
	}

	public SearchConditions addSearchCondition(SearchConditions searchConditions) {
		return sdRepository.save(searchConditions);
	}

}
