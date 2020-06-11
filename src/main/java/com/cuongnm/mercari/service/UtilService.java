package com.cuongnm.mercari.service;

import java.util.Date;
import java.util.Random;

import org.springframework.stereotype.Service;

@Service
public class UtilService {
	
	
	public String createCodeVerify() {
		int code = (int) Math.floor(((Math.random() * 899999) + 100000));
		return code + "";
	}
	
}
