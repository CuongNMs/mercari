package com.cuongnm.mercari.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cuongnm.mercari.model.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {

	Users findByUsername(String username);

	@Query(value = "SELECT id, username, status, first_name, last_name, avatar_path FROM users WHERE users.user_id= ?1", nativeQuery = true)
	Users getUserInfo(Long userId);

	@Query(value = "SELECT users.* FROM users INNER JOIN followers ON users.user_id = followers.user_id WHERE followers.follow_product_target = ?1", nativeQuery = true)
	public List<Users> findProductFollowers(Long productId);
	
	@Query(value = "SELECT users.* FROM users INNER JOIN followers ON users.user_id = followers.user_id WHERE followers.follow_user_target = ?1", nativeQuery = true)
	public List<Users> findUserFollowers(Long userId);
	
}
