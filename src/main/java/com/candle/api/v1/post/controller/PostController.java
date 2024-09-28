package com.candle.api.v1.post.controller;

import com.candle.api.v1.post.dto.request.WritingDiaryRequest;
import com.candle.api.v1.post.dto.response.DiaryResponse;
import com.candle.api.v1.post.dto.response.WritingDiaryResponse;
import com.candle.api.v1.post.service.PostService;
import org.springframework.web.bind.annotation.*;

@RestController
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/diary")
    public WritingDiaryResponse writingDiary(@RequestBody WritingDiaryRequest writingDiaryRequest) {
        return postService.writingDiary(writingDiaryRequest);
    }

    @GetMapping("/diary")
    public DiaryResponse getDiary() {
        return postService.getDiary();
    }

    @DeleteMapping("/diary/{id}")
    public int deleteDiary(@PathVariable int id) {
        return postService.deleteDiary(id);
    }

}
