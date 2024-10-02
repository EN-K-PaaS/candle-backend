package com.candle.api.v1.domain.diary.controller;

import com.candle.api.v1.domain.diary.dto.request.UpdatedDiaryRequest;
import com.candle.api.v1.domain.diary.dto.request.WrittenDiaryRequest;
import com.candle.api.v1.domain.diary.dto.response.DiaryResponse;
import com.candle.api.v1.domain.diary.dto.response.WrittenDiaryResponse;
import com.candle.api.v1.domain.diary.service.DiaryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/diary")
public class DiaryController {

    private final DiaryService diaryService;

    public DiaryController(DiaryService diaryService) {
        this.diaryService = diaryService;
    }

    @PostMapping("/")
    public WrittenDiaryResponse writingDiary(@RequestBody WrittenDiaryRequest writtenDiaryRequest) {
        return diaryService.writingDiary(writtenDiaryRequest);
    }

    @PutMapping("/")
    public Integer updateDiary(@RequestBody UpdatedDiaryRequest updatedDiaryRequest) {
        return diaryService.updateDiary(updatedDiaryRequest);
    }

    @GetMapping("/")
    public List<DiaryResponse> getDiary(@RequestParam String id) {
        return diaryService.getDiary(id);
    }

    @DeleteMapping("/")
    public Integer deleteDiary(@RequestParam Integer id) {
        return diaryService.deleteDiary(id);
    }
}
