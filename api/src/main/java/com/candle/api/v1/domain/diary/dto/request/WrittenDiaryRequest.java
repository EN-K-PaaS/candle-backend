package com.candle.api.v1.domain.diary.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record WrittenDiaryRequest(@NotBlank String userId, String title, @NotNull String content, String image,
                                  @NotNull LocalDateTime createdAt) {
}
