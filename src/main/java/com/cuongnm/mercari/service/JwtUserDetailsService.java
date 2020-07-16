package com.cuongnm.mercari.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cuongnm.mercari.config.JwtTokenUtil;
import com.cuongnm.mercari.model.JwtBlacklist;
import com.cuongnm.mercari.model.Users;
import com.cuongnm.mercari.repository.JwtBlacklistRepository;
import com.cuongnm.mercari.repository.UserRepository;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private JwtTokenUtil jtUtil;

	@Autowired
	public JwtBlacklistRepository jwtBlacklistRepository;

	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users user = userRepository.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException(username);
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				new ArrayList<>());
	}

	public String getUsernameFromToken(String token) {
		return jtUtil.getUsernameFromToken(token);
	}

	public Users getUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	public JwtBlacklist getJwtBlacklistToken(String token) {
		return jwtBlacklistRepository.findByTokenEquals(token);
	}

	public JwtBlacklist saveJwtBlacklistToken(JwtBlacklist jwtBlacklist) {
		return jwtBlacklistRepository.save(jwtBlacklist);
	}

	public Users updatePassword(String username, String password) {
		Users user = userRepository.findByUsername(username);
		if (user == null)
			return null;
		user.setPassword(password);
		return userRepository.save(user);
	}

	public void clearTokenBacklistTable() {
		List<JwtBlacklist> list = jwtBlacklistRepository.findTokenByExpiredDate();
		for (JwtBlacklist jwtBlacklist : list) {
			jwtBlacklistRepository.delete(jwtBlacklist);
		}
	}

	public Users save(Users newUser) {
		Users user = new Users();
		user.setUsername(newUser.getUsername());
		user.setPassword(bcryptEncoder.encode(newUser.getPassword()));
		return userRepository.save(user);
	}

}
