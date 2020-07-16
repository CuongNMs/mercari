package com.cuongnm.mercari.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.cuongnm.mercari.repository.UserRepository;

@Service("appAuthorizer")
public class AppAuthorizerImpl implements AppAuthorizer {

	private final Logger logger = LoggerFactory.getLogger(AppAuthorizerImpl.class);

	@Autowired
	public UserRepository uRepository;

	@Override
	public boolean authorize(Authentication authentication, Object callerObj) {
		boolean isAllow = false;
		try {
			UsernamePasswordAuthenticationToken user = (UsernamePasswordAuthenticationToken) authentication;
			if (user == null) {
				return isAllow;
			}
			String username = user.getName();
			int role = uRepository.findByUsername(username).getRole();
			if (role == 0)
				isAllow = true;

		} catch (Exception e) {
			logger.error(e.toString(), e);
			throw e;
		}
		return isAllow;
	}

}
