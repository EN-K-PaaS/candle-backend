package com.candle.api.v1.domain.diary.service;

import com.candle.api.v1.domain.diary.entity.Diary;
import com.candle.api.v1.domain.diary.repository.DiaryRepository;
import com.candle.api.v1.domain.diary.dto.request.UpdatedDiaryRequest;
import com.candle.api.v1.domain.diary.dto.request.WrittenDiaryRequest;
import com.candle.api.v1.domain.diary.dto.response.DiaryResponse;
import com.candle.api.v1.domain.diary.dto.response.WrittenDiaryResponse;
import com.candle.api.v1.domain.user.entity.User;
import com.candle.api.v1.domain.user.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DiaryService {
    private final DiaryRepository diaryRepository;
    private final UserService userService;

    public DiaryService(DiaryRepository diaryRepository, UserService userService) {
        this.diaryRepository = diaryRepository;
        this.userService = userService;
    }

    @Transactional
    public WrittenDiaryResponse writingDiary(WrittenDiaryRequest writtenDiaryRequest) {
        String content = writtenDiaryRequest.content();
        String title = writtenDiaryRequest.title();
        String image = writtenDiaryRequest.image();
        LocalDateTime createdAt = writtenDiaryRequest.createdAt();
        User user = userService.findById(writtenDiaryRequest.userId());
        Diary diary = new Diary(user, title, content, image, createdAt);

        diaryRepository.save(diary);
        return new WrittenDiaryResponse(diary.getId(), "다이어리 작성 완료");
    }

    @Transactional
    public Integer updateDiary(UpdatedDiaryRequest updatedDiaryRequest) {
        Integer id = updatedDiaryRequest.id();

        if (!diaryRepository.existsById(id)) {
            throw new IllegalArgumentException("해당 id에 대한 post가 존재하지 않습니다.");
        }

        Diary diary = diaryRepository.findById(id).get();

        diary.getPost().setContent(updatedDiaryRequest.content());
        diary.getPost().setImage(updatedDiaryRequest.image());
        diary.getPost().setCreatedAt(updatedDiaryRequest.createdAt());
        return diary.getId();
    }

    @Transactional(readOnly = true)
    public List<DiaryResponse> getDiary(String userId) {
        List<Diary> diaries = diaryRepository.findByUserId(userId);

        if (diaries.isEmpty()) {
            throw new IllegalArgumentException("해당 id에 대한 다이어리가 존재하지 않습니다.");
        }

        return diaries.stream()
                .map(diary -> new DiaryResponse(diary.getId(), diary.getTitle(), diary.getPost().getContent(), diary.getPost().getImage(), diary.getPost().getCreatedAt()))
                .toList();
    }

    @Transactional
    public Integer deleteDiary(Integer id) {
        if (!diaryRepository.existsById(id)) {
            throw new IllegalArgumentException("해당 id에 대한 post가 존재하지 않습니다.");
        }
        diaryRepository.deleteById(id);
        return id;
    }
}
