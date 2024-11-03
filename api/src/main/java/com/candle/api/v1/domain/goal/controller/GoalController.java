package com.candle.api.v1.domain.goal.controller;

import com.candle.api.v1.domain.goal.dto.request.GoalRequest;
import com.candle.api.v1.domain.goal.dto.request.UpdatedGoalRequest;
import com.candle.api.v1.domain.goal.dto.response.GoalResponse;
import com.candle.api.v1.domain.goal.service.GoalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/goals")
@Tag(name = "Goal", description = "목표 관련 API")
public class GoalController {

    private final GoalService goalService;

    public GoalController(GoalService goalService) {
        this.goalService = goalService;
    }

    @Operation(summary = "목표 생성", description = "목표를 생성합니다.")
    @PostMapping
    public Integer createGoal(@RequestBody GoalRequest goalRequest) {
        return goalService.writeGoal(goalRequest);
    }

    @Operation(summary = "목표 조회", description = "목표를 조회합니다.")
    @GetMapping
    public List<GoalResponse> getGoals(@RequestParam String userId) {
        return goalService.getGoals(userId);
    }

    @Operation(summary = "목표 삭제", description = "목표를 삭제합니다.")
    @DeleteMapping("/{id}")
    public Integer deleteGoal(@PathVariable Integer id) {
        return goalService.deleteGoal(id);
    }

    @Operation(summary = "목표 수정", description = "목표를 수정합니다.")
    @PutMapping
    public Integer updateGoal(@RequestBody UpdatedGoalRequest updatedGoalRequest) {
        return goalService.updateGoal(updatedGoalRequest);
    }
}
