package com.candle.api.v1.domain.community.controller;

import com.candle.api.v1.domain.community.dto.request.LikeRequest;
import com.candle.api.v1.domain.community.service.CommunityService;
import com.candle.api.v1.domain.community.dto.request.CommunityRequest;
import com.candle.api.v1.domain.community.dto.response.CommunityResponse;
import com.candle.api.v1.domain.community.service.LikeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/community")
public class CommunityController {

    private final CommunityService communityService;
    private final LikeService likeService;

    public CommunityController(CommunityService communityService, LikeService likeService) {
        this.communityService = communityService;
        this.likeService = likeService;
    }

    @PostMapping("/")
    public Integer writingCommunity(@RequestBody CommunityRequest communityRequest) {
        return communityService.writingCommunity(communityRequest);
    }

    @GetMapping("/")
    public List<CommunityResponse> getCommunity() {
        return communityService.getCommunity();
    }

    @DeleteMapping("/")
    public Integer deleteCommunity(@RequestParam Integer id) {
        return communityService.deleteCommunity(id);
    }

    @PostMapping("/like")
    public Integer like(@RequestBody LikeRequest likeRequest) {
        return likeService.like(likeRequest);
    }

    @DeleteMapping("/like")
    public Integer unlike(@RequestParam Integer likeId) {
        return likeService.unlike(likeId);
    }
}
