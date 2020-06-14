package com.cuongnm.mercari.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cuongnm.mercari.config.JwtTokenUtil;
import com.cuongnm.mercari.model.Users;
import com.cuongnm.mercari.service.JwtUserDetailsService;
import com.cuongnm.mercari.utils.DefaultRequest;
import com.cuongnm.mercari.utils.JwtRequest;
import com.cuongnm.mercari.utils.JwtResponse;

@RestController
@CrossOrigin
public class JwtAuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService userDetailsService;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

		final String token = jwtTokenUtil.generateToken(userDetails);
		Users user = userDetailsService.getUserByUsername(authenticationRequest.getUsername());
		
		return ResponseEntity.ok(new JwtResponse(token, user.getUserId().toString(), user.getUsername(), (user.getAvatar() == null && (user.getStatus() == null || user.getStatus() == 1 || user.getStatus() == 0) ? "-1":"1" )));
	}

	
	
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public ResponseEntity<?> logout(@RequestBody DefaultRequest logoutRequest) throws Exception {
		// Todo code
		return null;
	}
	

	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<?> saveUser(@RequestBody Users user) throws Exception {
		return ResponseEntity.ok(userDetailsService.save(user));
	}


	
	@RequestMapping(value = "/reset", method = RequestMethod.POST)
	public ResponseEntity<?> resetPassword(@RequestBody JwtRequest resetRequest) throws Exception {
		userDetailsService.updatePassword(resetRequest.getUsername(), resetRequest.getPassword());
		authenticate(resetRequest.getUsername(), resetRequest.getPassword());
		final UserDetails userDetails = userDetailsService.loadUserByUsername(resetRequest.getUsername());
		final String token = jwtTokenUtil.generateToken(userDetails);
		Users user = userDetailsService.getUserByUsername(resetRequest.getUsername());
		return ResponseEntity.ok(new JwtResponse(token, user.getUserId().toString(), user.getUsername(), (user.getAvatar() == null && (user.getStatus() == null || user.getStatus() == 1 || user.getStatus() == 0) ? "-1":"1" )));
	}
	
	
	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
	
	
}