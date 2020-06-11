package com.cuongnm.mercari.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cuongnm.mercari.service.UtilService;

@RestController
public class UserController{

	@Autowired
	private UtilService service;
	
	
	@RequestMapping({ "/create-code-reset-password" })
	public String firstPage() {
		return service.createCodeVerify();
	}

}