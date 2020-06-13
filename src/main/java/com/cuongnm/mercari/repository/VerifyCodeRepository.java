package com.cuongnm.mercari.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cuongnm.mercari.model.VerifyCodes;

@Repository
public interface VerifyCodeRepository extends JpaRepository<VerifyCodes, Long>{
	
	VerifyCodes findByUsername(String username);
}
