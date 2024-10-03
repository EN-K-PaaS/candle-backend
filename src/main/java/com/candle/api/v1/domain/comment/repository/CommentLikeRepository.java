package com.candle.api.v1.domain.comment.repository;

import com.candle.api.v1.domain.comment.entity.CommentLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentLikeRepository extends JpaRepository<CommentLike, Integer> {
//    boolean existsByCommunityIdAndUserId(Integer commentId, String userId);
}
