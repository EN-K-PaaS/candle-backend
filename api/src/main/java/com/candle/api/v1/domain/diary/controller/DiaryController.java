package com.candle.api.v1.domain.diary.controller;

import com.candle.api.v1.domain.diary.dto.request.UpdatedDiaryRequest;
import com.candle.api.v1.domain.diary.dto.request.WrittenDiaryRequest;
import com.candle.api.v1.domain.diary.dto.response.DiaryResponse;
import com.candle.api.v1.domain.diary.dto.response.WrittenDiaryResponse;
import com.candle.api.v1.domain.diary.service.DiaryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/diaries")
@Tag(name = "Diary", description = "다이어리 관련 API")
public class DiaryController {

    private final DiaryService diaryService;

    public DiaryController(DiaryService diaryService) {
        this.diaryService = diaryService;
    }

    @Operation(summary = "다이어리 작성", description = "다이어리를 작성합니다.")
    @PostMapping
    public WrittenDiaryResponse writingDiary(@RequestBody WrittenDiaryRequest writtenDiaryRequest) {
        return diaryService.writingDiary(writtenDiaryRequest);
    }

    @Operation(summary = "다이어리 수정", description = "다이어리를 수정합니다.")
    @PutMapping
    public Integer updateDiary(@RequestBody UpdatedDiaryRequest updatedDiaryRequest) {
        return diaryService.updateDiary(updatedDiaryRequest);
    }

    @Operation(summary = "다이어리 조회", description = "다이어리를 조회합니다.")
    @GetMapping
    public List<DiaryResponse> getDiary(@RequestParam String userId) {
        return diaryService.getDiary(userId);
    }

    @Operation(summary = "다이어리 삭제", description = "다이어리를 삭제합니다.")
    @DeleteMapping("/{id}")
    public Integer deleteDiary(@PathVariable Integer id) {
        return diaryService.deleteDiary(id);
    }
}
