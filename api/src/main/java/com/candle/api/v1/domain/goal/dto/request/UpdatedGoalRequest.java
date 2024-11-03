package com.candle.api.v1.domain.goal.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record UpdatedGoalRequest(@NotNull Integer id, @NotBlank String userId, @NotBlank String title, @NotNull Double progress, @NotBlank LocalDateTime goalDate, @NotNull boolean isFinished) {
}
