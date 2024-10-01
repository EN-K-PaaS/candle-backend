package com.candle.api.v1.post.dto.response;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record WrittenDiaryResponse(@NotNull Integer id, @NotBlank String comment) {
}
