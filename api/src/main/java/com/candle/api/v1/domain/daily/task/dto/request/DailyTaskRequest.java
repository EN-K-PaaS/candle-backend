package com.candle.api.v1.domain.daily.task.dto.request;

import jakarta.validation.constraints.NotBlank;

public record DailyTaskRequest(@NotBlank String userId, @NotBlank String title, @NotBlank Integer priority) {
}
