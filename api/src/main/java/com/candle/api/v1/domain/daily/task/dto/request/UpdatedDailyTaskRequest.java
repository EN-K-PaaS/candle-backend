package com.candle.api.v1.domain.daily.task.dto.request;

import jakarta.validation.constraints.NotNull;

public record UpdatedDailyTaskRequest(@NotNull Integer id, @NotNull String title) {
}
