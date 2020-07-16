package com.cuongnm.mercari.service;

import org.springframework.security.core.Authentication;

public interface AppAuthorizer {
	boolean authorize(Authentication authentication, Object callerObj);
}
