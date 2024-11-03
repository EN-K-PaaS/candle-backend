package com.candle.api.v1.domain.diary.dto.response;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DiaryResponse(@NotNull Integer id, String title, @NotNull String content, String image, @NotNull LocalDateTime createdAt) {
}
