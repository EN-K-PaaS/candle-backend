package com.candle.api.v1.domain.community.repository;

import com.candle.api.v1.domain.community.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<Like, Integer> {
    boolean existsByCommunityIdAndUserId(Integer communityId, String userId);
}
