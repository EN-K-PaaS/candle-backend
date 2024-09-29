package com.candle.api.v1.user.repository;

import com.candle.api.v1.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    boolean existsByIdAndPassword(String id, String password);
}
