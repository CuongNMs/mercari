package com.cuongnm.mercari.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cuongnm.mercari.model.Users;
import com.cuongnm.mercari.model.VerifyCodes;

@Transactional
@Repository
public interface VerifyCodeRepository extends JpaRepository<VerifyCodes, Long>{
	VerifyCodes findByVerifyCode(String verifyCode);
	
	void deleteByUsers(Users users);
}
