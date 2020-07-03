package com.cuongnm.mercari.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cuongnm.mercari.model.JwtBlacklist;

public interface JwtBlacklistRepository extends JpaRepository<JwtBlacklist, Long> {

	JwtBlacklist findByTokenEquals(String token);

}
