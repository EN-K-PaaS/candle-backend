package com.candle.api.v1.domain.community.repository;

import com.candle.api.v1.domain.community.entity.Community;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommunityRepository extends JpaRepository<Community, Integer> {
//    @Query("SELECT p FROM Community p WHERE p.user.id = :id")
    List<Community> findByUserId(String id);
}
