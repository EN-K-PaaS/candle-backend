package com.candle.api.v1.post.controller;

import com.candle.api.v1.post.dto.request.CommunityRequest;
import com.candle.api.v1.post.dto.request.LikeRequest;
import com.candle.api.v1.post.dto.request.UpdatedDiaryRequest;
import com.candle.api.v1.post.dto.request.WrittenDiaryRequest;
import com.candle.api.v1.post.dto.response.CommunityResponse;
import com.candle.api.v1.post.dto.response.DiaryResponse;
import com.candle.api.v1.post.dto.response.WrittenDiaryResponse;
import com.candle.api.v1.post.service.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/post")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    // ======== 일기 작성, 조회, 삭제 API ========
    @PostMapping("/diary")
    public WrittenDiaryResponse writingDiary(@RequestBody WrittenDiaryRequest writtenDiaryRequest) {
        return postService.writingDiary(writtenDiaryRequest);
    }

    @PutMapping("/diary")
    public Integer updateDiary(@RequestBody UpdatedDiaryRequest updatedDiaryRequest) {
        return postService.updateDiary(updatedDiaryRequest);
    }

    @GetMapping("/diary")
    public List<DiaryResponse> getDiary(@RequestParam String id) {
        return postService.getDiary(id);
    }

    @DeleteMapping("/diary")
    public Integer deleteDiary(@RequestParam Integer id) {
        return postService.deletePost(id);
    }

    // ======== 게시글 작성, 조회, 삭제 API ========
    @PostMapping("/community")
    public Integer writingCommunity(@RequestBody CommunityRequest communityRequest) {
        return postService.writingCommunity(communityRequest);
    }

    @GetMapping("/community")
    public List<CommunityResponse> getCommunity() {
        return postService.getCommunity();
    }

    @DeleteMapping("/community")
    public Integer deleteCommunity(@RequestParam Integer id) {
        return postService.deletePost(id);
    }

    // ======== 게시글 좋아요 개수 증가, 감소 API ========
    @PostMapping("/community/like/increase")
    public Integer increaseLike(@RequestBody LikeRequest likeRequest) {
        return postService.increaseLike(likeRequest);
    }

    @PostMapping("/community/like/decrease")
    public Integer decreaseLike(@RequestBody LikeRequest likeRequest) {
        return postService.decreaseLike(likeRequest);
    }
}
