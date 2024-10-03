package com.candle.api.v1.domain.community.repository;

import com.candle.api.v1.domain.community.entity.CommunityLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CommunityLikeRepository extends JpaRepository<CommunityLike, Integer> {

//    @Query("SELECT c FROM Community c WHERE c.id = :communityId AND c.user.id = :userId")
//    boolean existsByCommunityIdAndUserId(Integer communityId, String userId);
}
