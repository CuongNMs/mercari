package com.cuongnm.mercari.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cuongnm.mercari.model.Reactions;

@Repository
public interface ReactionRepository extends JpaRepository<Reactions, Long> {

	@Query(value = "SELECT * FROM reactions WHERE user_id = ?1 AND reaction_type = 4", nativeQuery = true)
	public List<Reactions> getListBlock(Long userId);

	@Query(value = "SELECT * FROM reactions WHERE user_id = ?1 AND reaction_type = 0", nativeQuery = true)
	public List<Reactions> getListLike(Long userId);

	@Query(value = "SELECT * FROM reactions WHERE user_id = ?1 AND reaction_type = 1", nativeQuery = true)
	public List<Reactions> getAllUserListRate(Long userId);

	@Query(value = "SELECT * FROM reactions WHERE target_user_id = ?1 AND reaction_type = 1", nativeQuery = true)
	public List<Reactions> getAllListRateOfUser(Long targetUserId);

	@Query(value = "SELECT * FROM reactions WHERE user_id = ?1 AND reaction_type = 1 AND rate = ?2", nativeQuery = true)
	public List<Reactions> getAllUserListRateByLevel(Long userId, int level);

	@Query(value = "SELECT * FROM reactions WHERE user_id = ?1 AND reaction_type = 1 AND rate = ?2", nativeQuery = true)
	public List<Reactions> getAllListRateOfUserByLevel(Long targetUserId, int level);

	@Query(value = "SELECT reactions.content, users.user_id, users.username, users.avatar_path FROM reactions INNER JOIN users ON reactions.user_id = users.user_id WHERE product_id = ?1 ", nativeQuery = true)
	public List<Object[]> getCommentProduct(Long productId);

}
