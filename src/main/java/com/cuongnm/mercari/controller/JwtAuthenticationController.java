package com.cuongnm.mercari.controller;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cuongnm.mercari.config.JwtTokenUtil;
import com.cuongnm.mercari.exception.UserInvalidException;
import com.cuongnm.mercari.model.JwtBlacklist;
import com.cuongnm.mercari.model.Users;

import com.cuongnm.mercari.service.JwtUserDetailsService;
import com.cuongnm.mercari.service.UtilService;
import com.cuongnm.mercari.utils.ChangePasswordRequest;
import com.cuongnm.mercari.utils.DefaultResponse;
import com.cuongnm.mercari.utils.ExceptionResponse;
import com.cuongnm.mercari.utils.JwtRequest;
import com.cuongnm.mercari.utils.JwtResponse;
import com.cuongnm.mercari.utils.ResetRequest;

@RestController
@CrossOrigin
public class JwtAuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UtilService uService;

	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Autowired
	private JwtUserDetailsService userDetailsService;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) {
		try {
			authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
			final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
			final String token = jwtTokenUtil.generateToken(userDetails);
			Users user = userDetailsService.getUserByUsername(authenticationRequest.getUsername());
			return ResponseEntity.ok(new DefaultResponse("1000", "OK", new JwtResponse(user.getUserId().toString(),
					user.getUsername(), token, user.getAvatarPath() == null ? user.getAvatarPath() : "-1")));
		} catch (Exception e) {
			return new ResponseEntity<ExceptionResponse>(new ExceptionResponse("9995", "User is not validated"),
					HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/customLogout", method = RequestMethod.POST)
	public ResponseEntity<?> customLogout(@RequestHeader String Authorization) {
		try {
			userDetailsService.clearTokenBacklistTable();
			String token = Authorization.substring(7);
			JwtBlacklist jwtBlacklist = new JwtBlacklist();
			jwtBlacklist.setToken(token);
			jwtBlacklist.setExpiredDate(jwtTokenUtil.getExpirationDateFromToken(token));
			userDetailsService.saveJwtBlacklistToken(jwtBlacklist);
			SecurityContextHolder.getContext().setAuthentication(null);
			return ResponseEntity.ok(new DefaultResponse("1000", "OK", null));
		} catch (Exception e) {
			return new ResponseEntity<ExceptionResponse>(new ExceptionResponse("9999", "Exception error"),
					HttpStatus.BAD_REQUEST);
		}
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

	@RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
	public ResponseEntity<?> resetPassword(@RequestBody ResetRequest resetRequest) {
		try {
			Users user = userDetailsService.getUserByUsername(resetRequest.getUsername());
			String verifyCode = uService.getCodeVerify(resetRequest.getUsername());
			if (user != null && verifyCode != null) {
				uService.deleteVerifyCode(user);
				userDetailsService.updatePassword(resetRequest.getUsername(),
						bcryptEncoder.encode(resetRequest.getPassword()));
				authenticate(resetRequest.getUsername(), resetRequest.getPassword());
				final UserDetails userDetails = userDetailsService.loadUserByUsername(resetRequest.getUsername());
				final String token = jwtTokenUtil.generateToken(userDetails);
				return ResponseEntity.ok(new DefaultResponse("1000", "OK", new JwtResponse(user.getUserId().toString(),
						user.getUsername(), token, user.getAvatarPath() == null ? user.getAvatarPath() : "-1")));
			}
			return new ResponseEntity<ExceptionResponse>(new ExceptionResponse("9998", "Reset failed"),
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
				return ResponseEntity.ok(new DefaultResponse("1000", "OK", userDetailsService
						.updatePassword(users.getUsername(), bcryptEncoder.encode(changePwdRequest.getNewPassword()))));
			}
			return new ResponseEntity<ExceptionResponse>(new ExceptionResponse("1017", "Fail to change password"),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<ExceptionResponse>(new ExceptionResponse("9999", "Exception error"),
					HttpStatus.BAD_REQUEST);
		}
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