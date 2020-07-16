package com.cuongnm.mercari.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cuongnm.mercari.model.Users;
import com.cuongnm.mercari.model.VerifyCodes;

@Transactional
@Repository
public interface VerifyCodeRepository extends JpaRepository<VerifyCodes, Long> {
	VerifyCodes findByVerifyCode(String verifyCode);

	void deleteByUsers(Users users);

	@Query(value = "SELECT verify_code FROM verify_codes WHERE user_id = ?1 ORDER BY verify_code_id DESC LIMIT 1", nativeQuery = true)
	String getVerifyCodeByUserId(Long userId);
}
