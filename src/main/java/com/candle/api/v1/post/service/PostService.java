package com.candle.api.v1.post.service;

import com.candle.api.v1.post.dto.PostType;
import com.candle.api.v1.post.dto.request.WritingDiaryRequest;
import com.candle.api.v1.post.dto.response.DiaryResponse;
import com.candle.api.v1.post.dto.response.WritingDiaryResponse;
import com.candle.api.v1.post.entity.Post;
import com.candle.api.v1.post.repository.PostRepository;
import com.candle.api.v1.user.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final UserService userService;

    public PostService(PostRepository postRepository, UserService userService) {
        this.postRepository = postRepository;
        this.userService = userService;
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

        Post post = new Post(id, userService.findById(userId), PostType.DIARY, title, content, image, createdAt);
        postRepository.save(post);
        return new WritingDiaryResponse(id, "다이어리 작성 완료");  // comment 수정 필요
    }

    @Transactional(readOnly = true)
    public List<DiaryResponse> getDiary(String id) {
        List<Post> posts = postRepository.findByUserId(id);

        if (posts.isEmpty()) {
            throw new IllegalArgumentException("해당 id에 대한 다이어리가 존재하지 않습니다.");  // exception 수정 필요
        }

        return posts.stream()
                .map(post -> new DiaryResponse(post.getId(), post.getTitle(), post.getContent(), post.getImage(), post.getCreatedAt()))
                .toList();
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
