package com.cuongnm.mercari.service;

import java.util.List;
import java.util.Set;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cuongnm.mercari.exception.DataException;
import com.cuongnm.mercari.exception.UserInvalidException;
import com.cuongnm.mercari.model.Brands;
import com.cuongnm.mercari.model.Categories;
import com.cuongnm.mercari.model.News;
import com.cuongnm.mercari.model.Products;
import com.cuongnm.mercari.model.Reactions;
import com.cuongnm.mercari.model.Sizes;
import com.cuongnm.mercari.model.Users;
import com.cuongnm.mercari.model.VerifyCodes;
import com.cuongnm.mercari.repository.BrandRepository;
import com.cuongnm.mercari.repository.CategoryRepository;
import com.cuongnm.mercari.repository.FollowerRepository;
import com.cuongnm.mercari.repository.NewsRepository;
import com.cuongnm.mercari.repository.ProductRepository;
import com.cuongnm.mercari.repository.ReactionRepository;
import com.cuongnm.mercari.repository.SizeRepository;
import com.cuongnm.mercari.repository.UserRepository;
import com.cuongnm.mercari.repository.VerifyCodeRepository;

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

	public Products updateProduct(Products product) {
		return pRepository.save(product);
	}

	public void deleteProduct(String id) {
		pRepository.deleteById(Long.parseLong(id));
	}

	public Brands addBrand(Brands brand) {
		return bRepository.save(brand);
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

	public List<Brands> getAllBrand() throws DataException {
		List<Brands> result = null;

		result = bRepository.findAll();
		if (result == null) {
			throw new DataException("No Data or end of list data");
		}
		return result;
	}

	public List<Users> searchUser(String keyword) {
		return uRepository.findByFirstName(keyword);
	}

	public List<News> getAllNews() throws DataException {
		List<News> results = nRepository.findAll();
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

	public List<Categories> loadCategoryByCategoryId(Long categoryId) {

		return cRepository.findByCategoryId(categoryId);

	}

	public List<Categories> loadAllCategory() {
		return cRepository.findAll();
	}

	public Categories addCategory(Categories categories) {
		Categories result = cRepository.save(categories);
		if (result == null) {
			throw new IllegalArgumentException();
		}
		return result;
	}

//	public List<Followers> getFollowerUser(String userId) {
//		return fRepository.findByFollowUserId(Long.parseLong(userId));
//	}
//
//	public List<Followers> getFollowerOfUser(String userId) {
//		return fRepository.findByUserId(Long.parseLong(userId));
//	}

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

	public Sizes addSize(Sizes sizes) {
		return sRepository.save(sizes);
	}

}
