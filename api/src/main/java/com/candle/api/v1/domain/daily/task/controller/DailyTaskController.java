package com.candle.api.v1.domain.daily.task.controller;

import com.candle.api.v1.domain.daily.task.dto.request.DailyTaskRequest;
import com.candle.api.v1.domain.daily.task.dto.request.UpdatedDailyTaskRequest;
import com.candle.api.v1.domain.daily.task.dto.response.DailyTaskResponse;
import com.candle.api.v1.domain.daily.task.dto.response.LastWeekRateResponse;
import com.candle.api.v1.domain.daily.task.service.DailyTaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/daily-tasks") // 복수형으로 변경
@Tag(name = "DailyTask", description = "데일리 체크리스트 관련 API")
public class DailyTaskController {

    private final DailyTaskService dailyTaskService;

    public DailyTaskController(DailyTaskService dailyTaskService) {
        this.dailyTaskService = dailyTaskService;
    }

    @Operation(summary = "데일리 체크리스트 작성", description = "데일리 체크리스트를 작성합니다.")
    @PostMapping
    public Integer createDailyTask(@RequestBody DailyTaskRequest dailyTaskRequest) {
        return dailyTaskService.writeDailyTask(dailyTaskRequest);
    }

    @Operation(summary = "데일리 체크리스트 조회", description = "데일리 체크리스트를 조회합니다.")
    @GetMapping
    public List<DailyTaskResponse> getDailyTasks(@RequestParam String userId) {
        return dailyTaskService.getDailyTasks(userId);
    }

    @Operation(summary = "데일리 체크리스트 삭제", description = "데일리 체크리스트를 삭제합니다.")
    @DeleteMapping("/{id}")
    public Integer deleteDailyTask(@PathVariable Integer id) {
        return dailyTaskService.deleteDailyTask(id);
    }

    @Operation(summary = "데일리 체크리스트 목표 달성 업데이트", description = "데일리 체크리스트의 목표 달성을 업데이트합니다.")
    @PutMapping("/{id}/complete")
    public boolean updateBooleanDailyTask(@PathVariable Integer id) {
        return dailyTaskService.updateDailyTask(id);
    }

    @Operation(summary = "데일리 체크리스트 수정", description = "데일리 체크리스트를 수정합니다.")
    @PutMapping
    public Integer updateTitleDailyTask(@RequestBody UpdatedDailyTaskRequest updatedDailyTaskRequest) {
        return dailyTaskService.updateTitleDailyTask(updatedDailyTaskRequest);
    }

    @Operation(summary = "지난 일주일 감정 체크리스트 달성률 조회", description = "지난 일주일 동안의 체크리스트 달성률을 조회합니다.")
    @GetMapping("/rate")
    public LastWeekRateResponse getLastWeekRate(@RequestParam String userId) {
        return dailyTaskService.getLastWeekRate(userId);
    }
}