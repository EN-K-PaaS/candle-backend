package com.candle.api.v1.domain.daily.task.dto.response;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DailyTaskResponse(@NotNull Integer id, @NotBlank String userId, @NotBlank String title,
                                @NotNull Integer rank, @NotNull boolean isFinished, @NotNull LocalDateTime createdAt) {
}
