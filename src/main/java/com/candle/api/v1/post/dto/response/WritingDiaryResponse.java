package com.candle.api.v1.post.dto.response;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record WritingDiaryResponse(@NotNull Integer id, @NotBlank String comment) {
}
