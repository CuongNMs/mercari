package com.cuongnm.mercari.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cuongnm.mercari.model.Followers;

public interface FollowerRepository extends JpaRepository<Followers, Long> {
	
}
