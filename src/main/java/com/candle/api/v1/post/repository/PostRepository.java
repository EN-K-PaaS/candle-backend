package com.candle.api.v1.post.repository;

import com.candle.api.v1.post.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Integer> {
}
