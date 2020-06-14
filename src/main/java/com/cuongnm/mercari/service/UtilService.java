package com.cuongnm.mercari.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cuongnm.mercari.model.Followers;
import com.cuongnm.mercari.model.News;
import com.cuongnm.mercari.model.Users;
import com.cuongnm.mercari.model.VerifyCodes;
import com.cuongnm.mercari.repository.FollowerRepository;
import com.cuongnm.mercari.repository.NewsRepository;
import com.cuongnm.mercari.repository.UserRepository;
import com.cuongnm.mercari.repository.VerifyCodeRepository;

@Service
public class UtilService {

	@Autowired
	public VerifyCodeRepository vRepository;

	@Autowired
	public NewsRepository nRepository;

	@Autowired
	public FollowerRepository fRepository;
	
	@Autowired
	public UserRepository uRepository;
	

	public String createCodeVerify(String username) {
		String code = (int) Math.floor(((Math.random() * 899999) + 100000)) + "";
		vRepository.save(new VerifyCodes(code, username));
		return code;
	}

	public boolean compare(VerifyCodes verifyCodesObj) {
		VerifyCodes tmp = vRepository.findByUsername(verifyCodesObj.getUsername());
		if (verifyCodesObj.getUsername().equals(tmp.getUsername())
				&& verifyCodesObj.getVerifyCode().equals(tmp.getVerifyCode())) {
			return true;
		}
		return false;
	}
	
	public List<Users> searchUser(String keyword){
		return uRepository.findByFirstName(keyword);
	}

	public List<News> getAllNews() {
		return nRepository.findAll();
	}

	public News getNews(String id) {
		return nRepository.getOne(Long.parseLong(id));
	}

	public List<Followers> getFollowerUser(String userId) {
		return fRepository.findByFollowUserId(Long.parseLong(userId));
	}

	public List<Followers> getFollowerOfUser(String userId) {
		return fRepository.findByUserId(Long.parseLong(userId));
	}
	
	

}
