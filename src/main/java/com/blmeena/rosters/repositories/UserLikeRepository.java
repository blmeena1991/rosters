package com.blmeena.rosters.repositories;

import com.blmeena.rosters.models.UserLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserLikeRepository extends JpaRepository<UserLike, Long> {
    @Query(value = "select count(*) from user_likes i " +
            " where i.following_id = ?1 ", nativeQuery = true)
    Integer getUserVoteCount(Long id);
}