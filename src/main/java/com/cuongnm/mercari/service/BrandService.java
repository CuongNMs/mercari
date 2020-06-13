package com.cuongnm.mercari.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cuongnm.mercari.model.Brands;
import com.cuongnm.mercari.repository.BrandRepository;


@Service
public class BrandService {
	@Autowired
	private BrandRepository brandRepository;
	
	public List<Brands> getAllBrand(){
		return brandRepository.findAll();
	}
}
