package com.cuongnm.mercari.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cuongnm.mercari.model.Sizes;

@Repository
public interface SizeRepository extends JpaRepository<Sizes, Long> {

}
