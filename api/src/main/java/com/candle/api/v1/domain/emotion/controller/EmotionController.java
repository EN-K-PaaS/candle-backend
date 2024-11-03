package com.candle.api.v1.domain.emotion.controller;

import com.candle.api.v1.domain.emotion.dto.EmotionResponse;
import com.candle.api.v1.domain.emotion.dto.Period;
import com.candle.api.v1.domain.emotion.service.EmotionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/emotions")
@Tag(name = "Emotion", description = "감정 API")
public class EmotionController {

    private final EmotionService emotionService;

    public EmotionController(EmotionService emotionService) {
        this.emotionService = emotionService;
    }

    @Operation(summary = "감정 조회", description = "감정을 조회합니다.")
    @GetMapping("/last-month")
    public EmotionResponse getEmotionLastMonth(@RequestParam String userId) {
        return emotionService.getEmotion(userId, Period.LAST_MONTH);
    }

    @Operation(summary = "감정 조회", description = "감정을 조회합니다.")
    @GetMapping("/last-day")
    public EmotionResponse getEmotionLastDay(@RequestParam String userId) {
        return emotionService.getEmotion(userId, Period.LAST_DAY);
    }
}
