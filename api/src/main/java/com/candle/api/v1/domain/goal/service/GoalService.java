package com.candle.api.v1.domain.goal.service;

import com.candle.api.v1.domain.goal.dto.request.GoalRequest;
import com.candle.api.v1.domain.goal.dto.request.UpdatedGoalRequest;
import com.candle.api.v1.domain.goal.dto.response.GoalResponse;
import com.candle.api.v1.domain.goal.entity.Goal;
import com.candle.api.v1.domain.goal.repository.GoalRepository;
import com.candle.api.v1.domain.user.entity.User;
import com.candle.api.v1.domain.user.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class GoalService {

    private final GoalRepository goalRepository;
    private final UserService userService;

    public GoalService(GoalRepository goalRepository, UserService userService) {
        this.goalRepository = goalRepository;
        this.userService = userService;
    }

    @Transactional
    public Integer writeGoal(GoalRequest goalRequest) {
        String title = goalRequest.title();
        Double progress = goalRequest.progress();
        LocalDateTime goalDate = goalRequest.goalDate();
        String userId = goalRequest.userId();
        User user;
        Goal goal;

        if (progress < 0 || progress > 100) {
            throw new IllegalArgumentException("progress는 0부터 100까지의 숫자만 가능합니다.");
        }

        if (goalDate.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("목표 날짜는 현재 날짜 이후여야 합니다.");
        }

        if (userService.findById(userId) == null) {
            throw new IllegalArgumentException("해당 id의 유저가 존재하지 않습니다.");
        }

        user = userService.findById(userId);
        goal = new Goal(title, user, progress, goalDate);
        goalRepository.save(goal);
        return goal.getId();
    }

    public List<GoalResponse> getGoals(String userId) {
        List<GoalResponse> result = new ArrayList<>();
        List<Goal> goals = goalRepository.findByUserId(userId);

        if (goals.isEmpty()) {
            throw new IllegalArgumentException("목표가 존재하지 않습니다.");
        }

        for (Goal goal : goals) {
            result.add(new GoalResponse(goal.getId(), goal.getUser().getId(), goal.getTitle(), goal.isFinished(), goal.getProgress(), goal.getGoalDate()));
        }
        return result;
    }

    @Transactional
    public Integer deleteGoal(Integer id) {
        Goal goal = goalRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 id의 목표가 존재하지 않습니다."));
        goalRepository.delete(goal);
        return goal.getId();
    }

    @Transactional
    public Integer updateGoal(UpdatedGoalRequest updatedGoalRequest) {
        goalRepository.findById(updatedGoalRequest.id()).orElseThrow(() -> new IllegalArgumentException("해당 id의 목표가 존재하지 않습니다."));
        Goal goal = goalRepository.findById(updatedGoalRequest.id()).get();
        goal.setTitle(updatedGoalRequest.title());
        goal.setFinished(updatedGoalRequest.isFinished());
        goal.setGoalDate(updatedGoalRequest.goalDate());
        goal.setProgress(updatedGoalRequest.progress());
        return goal.getId();
    }
}
