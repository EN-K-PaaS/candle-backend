package com.candle.api.v1.post.service;

import com.candle.api.v1.post.dto.PostType;
import com.candle.api.v1.post.dto.request.WritingDiaryRequest;
import com.candle.api.v1.post.dto.response.DiaryResponse;
import com.candle.api.v1.post.dto.response.WritingDiaryResponse;
import com.candle.api.v1.post.entity.Post;
import com.candle.api.v1.post.repository.PostRepository;
import com.candle.api.v1.user.entity.User;
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
        String userId = writingDiaryRequest.userId();
        String title = writingDiaryRequest.title();
        String content = writingDiaryRequest.content();
        String image = writingDiaryRequest.image();
        LocalDateTime createdAt = writingDiaryRequest.createdAt();
        
        if (!userService.existsById(userId)) {
            throw new IllegalArgumentException("해당 id에 대한 유저가 존재하지 않습니다.");  // exception 수정 필요
        }

        User user = userService.findById(userId);
        Post post = new Post(user, PostType.DIARY, title, content, image, createdAt);
        postRepository.save(post);
        return new WritingDiaryResponse(post.getId(), "다이어리 작성 완료");  // comment 수정 필요
    }

    @Transactional(readOnly = true)
    public List<DiaryResponse> getDiary(String userId) {
        List<Post> posts = postRepository.findAll();
        List<Post> diaries = posts.stream()
                .filter(post -> post.getUser().getId().equals(userId))
                .filter(post -> post.getType().equals(PostType.DIARY))
                .toList();

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
