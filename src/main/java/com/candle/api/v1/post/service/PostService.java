package com.candle.api.v1.post.service;

import com.candle.api.v1.post.dto.PostType;
import com.candle.api.v1.post.dto.request.WritingDiaryRequest;
import com.candle.api.v1.post.dto.response.DiaryResponse;
import com.candle.api.v1.post.dto.response.WritingDiaryResponse;
import com.candle.api.v1.post.entity.PostEntity;
import com.candle.api.v1.post.repository.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Transactional
    public WritingDiaryResponse writingDiary(WritingDiaryRequest writingDiaryRequest) {
        Integer id = writingDiaryRequest.id();
        String userId = writingDiaryRequest.userId();
        String title = writingDiaryRequest.title();
        String content = writingDiaryRequest.content();
        String image = writingDiaryRequest.image();
        LocalDateTime createdAt = writingDiaryRequest.createdAt();

        if (postRepository.existsById(id)) {
            throw new IllegalArgumentException("이미 존재하는 id 입니다.");  // exception 수정 필요
        }

        postRepository.save(new PostEntity(id, userId, PostType.DIARY, title, content, image, createdAt));
        return new WritingDiaryResponse(id, "다이어리 작성 완료");  // comment 수정 필요
    }

    @Transactional(readOnly = true)
    public List<DiaryResponse> getDiary(String id) {
        List<PostEntity> posts = postRepository.findAll();
        ArrayList<DiaryResponse> diaryResponses = new ArrayList<>();

        for (PostEntity post : posts) {
            if (post.getType() == PostType.DIARY && post.getUserId().equals(id)) {
                diaryResponses.add(new DiaryResponse(post.getId(), post.getTitle(), post.getContent(), post.getImage(), post.getCreatedAt()));
            }
        }

        if (diaryResponses.isEmpty()) {
            throw new IllegalArgumentException("해당 id에 대한 다이어리가 존재하지 않습니다.");  // exception 수정 필요
        }

        return diaryResponses;
    }

    @Transactional
    public Integer deleteDiary(Integer id) {
        if (!postRepository.existsById(id)) {
            throw new IllegalArgumentException("해당 id에 대한 다이어리가 존재하지 않습니다.");  // exception 수정 필요
        }

        postRepository.deleteById(id);
        return id;
    }
}
