package com.cuongnm.mercari.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cuongnm.mercari.model.Users;
import com.cuongnm.mercari.repository.UserRepository;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	
	@Autowired
	private PasswordEncoder bcryptEncoder;
	
	private Users user;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		 this.user = userRepository.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException(username);
		}
		return new org.springframework.security.core.userdetails.User(this.user.getUsername(), this.user.getPassword(),
				new ArrayList<>());
	}
	
	
	public Users updatePassword(String username, String password) {
		Users user = userRepository.findByUsername(username);
		user.setPassword(password);
		return userRepository.save(user);
	}
	
	
	public Users save(Users newUser) {
		Users user = new Users();
		user.setUsername(newUser.getUsername());
		user.setPassword(bcryptEncoder.encode(newUser.getPassword()));
		return userRepository.save(user);
	}

	
	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

}
