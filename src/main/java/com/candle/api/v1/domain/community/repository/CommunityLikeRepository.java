package com.candle.api.v1.domain.community.repository;

import com.candle.api.v1.domain.community.entity.CommunityLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommunityLikeRepository extends JpaRepository<CommunityLike, Integer> {
    boolean existsByCommunityIdAndUserId(Integer communityId, String userId);
}
