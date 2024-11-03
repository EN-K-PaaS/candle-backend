package com.candle.api.v1.domain.goal.dto.request;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public record GoalRequest(@NotBlank String userId, @NotBlank String title, @NotBlank Double progress, @NotBlank LocalDateTime goalDate) {
}
