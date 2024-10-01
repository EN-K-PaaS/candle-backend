package com.candle.api.v1.domain.community.repository;

import com.candle.api.v1.domain.community.entity.Community;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommunityRepository extends JpaRepository<Community, Integer> {
    //    @Query("SELECT p FROM Post p WHERE p.user.id = :id")
//    List<Post> findByUserId(@Param("id") String id);
}
