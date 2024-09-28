package com.candle.api.v1.post.dto.request;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public record WritingDiaryRequest(@NotBlank int id, @NotBlank String userId, @NotBlank String title, @NotBlank String content, String image,
                                  @NotBlank LocalDateTime createdAt) {
}
