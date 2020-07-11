package com.cuongnm.mercari.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cuongnm.mercari.model.JwtBlacklist;

public interface JwtBlacklistRepository extends JpaRepository<JwtBlacklist, Long> {

	JwtBlacklist findByTokenEquals(String token);
	
	@Query(value = "select * from jwt_blacklist where jwt_blacklist.expired_date <= current_timestamp", nativeQuery = true)
	List<JwtBlacklist> findTokenByExpiredDate();

}
