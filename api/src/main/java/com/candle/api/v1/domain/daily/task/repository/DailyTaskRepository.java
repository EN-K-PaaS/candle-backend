package com.candle.api.v1.domain.daily.task.repository;

import com.candle.api.v1.domain.daily.task.entity.DailyTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DailyTaskRepository extends JpaRepository<DailyTask, Integer> {
    List<DailyTask> findByUserId(String userId);
}
