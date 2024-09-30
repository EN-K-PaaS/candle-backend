package com.candle.api.v1.post.service;

import com.candle.api.v1.post.dto.PostType;
import com.candle.api.v1.post.dto.request.CommunityRequest;
import com.candle.api.v1.post.dto.request.WrittenDiaryRequest;
import com.candle.api.v1.post.dto.response.CommunityResponse;
import com.candle.api.v1.post.dto.response.DiaryResponse;
import com.candle.api.v1.post.dto.response.WrittenDiaryResponse;
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
    public WrittenDiaryResponse writingDiary(WrittenDiaryRequest writtenDiaryRequest) {
        String userId = writtenDiaryRequest.userId();
        String title = writtenDiaryRequest.title();
        String content = writtenDiaryRequest.content();
        String image = writtenDiaryRequest.image();
        LocalDateTime createdAt = writtenDiaryRequest.createdAt();
        
        if (!userService.existsById(userId)) {
            throw new IllegalArgumentException("해당 id에 대한 유저가 존재하지 않습니다.");  // exception 수정 필요
        }

        User user = userService.findById(userId);
        Post post = new Post(user, PostType.DIARY, title, content, image, createdAt);
        postRepository.save(post);
        return new WrittenDiaryResponse(post.getId(), "다이어리 작성 완료");  // comment 수정 필요
    }

    @Transactional(readOnly = true)
    public List<DiaryResponse> getDiary(String userId) {
        List<Post> diaries = postRepository.findAll().stream()
                .filter(post -> post.getUser().getId().equals(userId))
                .filter(post -> post.getType().equals(PostType.DIARY))
                .toList();

        if (diaries.isEmpty()) {
            throw new IllegalArgumentException("해당 id에 대한 다이어리가 존재하지 않습니다.");  // exception 수정 필요
        }

        return diaries.stream()
                .map(diary -> new DiaryResponse(diary.getId(), diary.getTitle(), diary.getContent(), diary.getImage(), diary.getCreatedAt()))
                .toList();
    }

    @Transactional
    public Integer deletePost(Integer id) {
        if (!postRepository.existsById(id)) {
            throw new IllegalArgumentException("해당 id에 대한 post가 존재하지 않습니다.");  // exception 수정 필요
        }

        postRepository.deleteById(id);
        return id;
    }

    @Transactional
    public Integer writingCommunity(CommunityRequest communityRequest) {
        String userId = communityRequest.userId();
        String title = communityRequest.title();
        String content = communityRequest.content();
        String image = communityRequest.image();
        LocalDateTime createdAt = LocalDateTime.now();

        if (!userService.existsById(userId)) {
            throw new IllegalArgumentException("해당 id에 대한 유저가 존재하지 않습니다.");  // exception 수정 필요
        }

        User user = userService.findById(userId);
        Post post = new Post(user, PostType.COMMUNITY, title, content, image, createdAt);
        postRepository.save(post);
        return post.getId();
    }

    @Transactional(readOnly = true)
    public List<CommunityResponse> getCommunity() {
        List<Post> posts = postRepository.findAll();
        List<Post> communities = posts.stream()
                .filter(post -> post.getType().equals(PostType.COMMUNITY))
                .toList();

        if (communities.isEmpty()) {
            throw new IllegalArgumentException("커뮤니티 게시글이 존재하지 않습니다.");  // exception 수정 필요
        }

        return communities.stream()
                .map(post -> new CommunityResponse(post.getUser().getId(), post.getUser().getName(), post.getId(), post.getTitle(), post.getContent(), post.getImage(), post.getCreatedAt(), post.getLikeCount()))
                .toList();
    }
}
