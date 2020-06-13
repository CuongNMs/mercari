package com.cuongnm.mercari.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cuongnm.mercari.model.News;
import com.cuongnm.mercari.model.VerifyCodes;
import com.cuongnm.mercari.repository.NewsRepository;
import com.cuongnm.mercari.repository.VerifyCodeRepository;

@Service
public class UtilService {

	@Autowired
	public VerifyCodeRepository vRepository;

	@Autowired
	public NewsRepository nRepository;

	public String createCodeVerify(String username) {
		String code = (int) Math.floor(((Math.random() * 899999) + 100000)) + "";
		vRepository.save(new VerifyCodes(code, username));
		return code;
	}

	public boolean compare(VerifyCodes verifyCodesObj) {
		if (verifyCodesObj.equals(vRepository.findByUsername(verifyCodesObj.getUsername()))) {
			return true;
		}
		return false;
	}

	public List<News> getAllNews() {
		return nRepository.findAll();
	}

	public News getNews(String id) {
		return nRepository.getOne(Long.parseLong(id));
	}

}
