package com.candle.api.v1.post.service;

import com.candle.api.v1.post.dto.request.WritingDiaryRequest;
import com.candle.api.v1.post.dto.response.DiaryResponse;
import com.candle.api.v1.post.dto.response.WritingDiaryResponse;
import com.candle.api.v1.post.repository.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Transactional
    public WritingDiaryResponse writingDiary(WritingDiaryRequest writingDiaryRequest) {
        int id = writingDiaryRequest.id();
        String userId = writingDiaryRequest.userId();
        String title = writingDiaryRequest.title();
        String content = writingDiaryRequest.content();
        String image = writingDiaryRequest.image();
        LocalDateTime date = writingDiaryRequest.createdAt();

        return null;
    }

    @Transactional(readOnly = true)
    public DiaryResponse getDiary() {
        return null;
    }

    @Transactional
    public int deleteDiary(int id) {
        return 0;
    }
}
