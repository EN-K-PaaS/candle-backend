package com.candle.api.v1.domain.daily.task.service;

import com.candle.api.v1.domain.daily.task.dto.request.DailyTaskRequest;
import com.candle.api.v1.domain.daily.task.dto.request.UpdatedDailyTaskRequest;
import com.candle.api.v1.domain.daily.task.dto.response.DailyTaskResponse;
import com.candle.api.v1.domain.daily.task.dto.response.LastWeekRateResponse;
import com.candle.api.v1.domain.daily.task.entity.DailyTask;
import com.candle.api.v1.domain.daily.task.repository.DailyTaskRepository;
import com.candle.api.v1.domain.user.entity.User;
import com.candle.api.v1.domain.user.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class DailyTaskService {

    private final DailyTaskRepository dailyTaskRepository;
    private final UserService userService;

    public DailyTaskService(DailyTaskRepository dailyTaskRepository, UserService userService) {
        this.dailyTaskRepository = dailyTaskRepository;
        this.userService = userService;
    }

    @Transactional
    public Integer writeDailyTask(DailyTaskRequest dailyTaskRequest) {
        String title = dailyTaskRequest.title();
        Integer priority = dailyTaskRequest.priority();
        User user = userService.findById(dailyTaskRequest.userId());

        if (priority < 1 || priority > 5) {
            throw new IllegalArgumentException("priority는 1부터 5까지의 숫자만 가능합니다.");
        }

        DailyTask dailyTask = new DailyTask(title, user, priority, false, LocalDateTime.now());
        dailyTaskRepository.save(dailyTask);
        return dailyTask.getId();
    }

    public List<DailyTaskResponse> getDailyTasks(String userId) {
        List<DailyTaskResponse> result = new ArrayList<>();
        List<DailyTask> dailyTasks = dailyTaskRepository.findByUserId(userId);

        if (dailyTasks.isEmpty()) {
            throw new IllegalArgumentException("데일리 체크리스트가 존재하지 않습니다.");
        }

        for (DailyTask dailyTask : dailyTasks) {
            result.add(new DailyTaskResponse(dailyTask.getId(), dailyTask.getUser().getId(), dailyTask.getTitle(), dailyTask.getPriority(), dailyTask.isFinished(), dailyTask.getCreatedAt()));
        }
        return result;
    }

    @Transactional
    public Integer deleteDailyTask(Integer id) {
        DailyTask dailyTask = dailyTaskRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 id의 데일리 체크리스트가 존재하지 않습니다."));
        dailyTaskRepository.delete(dailyTask);
        return dailyTask.getId();
    }

    @Transactional
    public boolean updateDailyTask(Integer id) {
        DailyTask dailyTask = dailyTaskRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 id의 데일리 체크리스트가 존재하지 않습니다."));
        dailyTask.changeFinished();
        return dailyTask.isFinished();
    }

    @Transactional
    public Integer updateTitleDailyTask(UpdatedDailyTaskRequest updatedDailyTaskRequest) {
        Integer id = updatedDailyTaskRequest.id();
        String title = updatedDailyTaskRequest.title();

        DailyTask dailyTask = dailyTaskRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 id의 데일리 체크리스트가 존재하지 않습니다."));

        dailyTask.setTitle(title);
        return dailyTask.getId();
    }

    public LastWeekRateResponse getLastWeekRate(String userId) {
        return new LastWeekRateResponse(55.5, 44.5, 0.0, 33.7, 12.7, 88.8, 99.9);
    }
}
