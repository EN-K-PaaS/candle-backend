package com.candle.api.v1.domain.goal.dto.response;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record GoalResponse(@NotNull Integer id, @NotBlank String userId, @NotBlank String title, @NotNull boolean isFinished, @NotNull Double progress, @NotNull LocalDateTime goalDate) {
}
