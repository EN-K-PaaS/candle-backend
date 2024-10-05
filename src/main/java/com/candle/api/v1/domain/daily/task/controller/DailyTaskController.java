package com.candle.api.v1.domain.daily.task.controller;

import com.candle.api.v1.domain.daily.task.dto.request.DailyTaskRequest;
import com.candle.api.v1.domain.daily.task.dto.response.DailyTaskResponse;
import com.candle.api.v1.domain.daily.task.service.DailyTaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/daily-task")
@Tag(name = "DailyTask", description = "데일리 체크리스트 관련 API")
public class DailyTaskController {

    private final DailyTaskService dailyTaskService;

    public DailyTaskController(DailyTaskService dailyTaskService) {
        this.dailyTaskService = dailyTaskService;
    }

    @Operation(summary = "데일리 체크리스트 작성", description = "데일리 체크리스트를 작성합니다.")
    @PostMapping
    public Integer WriteDailyTask(@RequestBody DailyTaskRequest dailyTaskRequest) {
        return dailyTaskService.writeDailyTask(dailyTaskRequest);
    }

    @Operation(summary = "데일리 체크리스트 조회", description = "데일리 체크리스트를 조회합니다.")
    @GetMapping
    public List<DailyTaskResponse> getDailyTask() {
        return dailyTaskService.getDailyTasks();
    }

    @Operation(summary = "데일리 체크리스트 삭제", description = "데일리 체크리스트를 삭제합니다.")
    @DeleteMapping("/{id}")
    public Integer deleteDailyTask(@PathVariable Integer id) {
        return dailyTaskService.deleteDailyTask(id);
    }

    @Operation(summary = "데일리 체크리스트 업데이트", description = "데일리 체크리스트를 업데이트합니다.")
    @PostMapping("/{id}")
    public boolean updateBooleanDailyTask(@PathVariable Integer id) {
        return dailyTaskService.updateDailyTask(id);
    }

    @PostMapping("/{id}")
    public Integer updateTitleDailyTask(@PathVariable Integer id, @RequestParam String title) {
        return dailyTaskService.updateTitleDailyTask(id, title);
    }
}
