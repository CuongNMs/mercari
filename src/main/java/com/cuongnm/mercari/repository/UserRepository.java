package com.cuongnm.mercari.repository;


import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cuongnm.mercari.model.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {

	Users findByUsername(String username);


	List<Users> findByFirstName(String firstName);
	
}



