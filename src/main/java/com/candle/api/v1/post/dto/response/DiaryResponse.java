package com.candle.api.v1.post.dto.response;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DiaryResponse(@NotNull Integer id, @NotNull String title, @NotNull String content, String image, @NotNull LocalDateTime createdAt) {
}
