package com.cuongnm.mercari.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.cuongnm.mercari.model.Followers;

@Repository
public interface FollowerRepository extends JpaRepository<Followers, Long> {

}
