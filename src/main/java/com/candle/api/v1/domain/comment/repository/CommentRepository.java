package com.candle.api.v1.domain.comment.repository;

import com.candle.api.v1.domain.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

//    @Query("SELECT c FROM Comment c WHERE c.community.id = :communityId AND c.user.id = :userId")
//    boolean existsByCommunityIdAndUserId(Integer communityId, String userId);
}
