package com.candle.api.v1.domain.emotion.service;

import com.candle.api.v1.domain.emotion.dto.EmotionResponse;
import com.candle.api.v1.domain.emotion.dto.Period;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class EmotionService {


    public EmotionResponse getEmotion(String userId, Period period) {
        return new EmotionResponse(33.3, 33.3, 33.3);
    }
}
