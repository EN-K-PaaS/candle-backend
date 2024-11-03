package com.candle.api.v1.domain.emotion.dto;

import jakarta.validation.constraints.NotNull;

public record EmotionResponse(@NotNull Double positive, @NotNull Double neutral, @NotNull Double negative) {
}
