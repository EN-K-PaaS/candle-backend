package com.candle.api.v1.post.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record WritingDiaryRequest(@NotBlank String userId, @NotNull String title, @NotNull String content, String image,
                                  @NotNull LocalDateTime createdAt) {
}
