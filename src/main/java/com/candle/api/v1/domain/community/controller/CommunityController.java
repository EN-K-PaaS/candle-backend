package com.candle.api.v1.domain.community.controller;

import com.candle.api.v1.domain.community.dto.request.CommunityLikeRequest;
import com.candle.api.v1.domain.community.service.CommunityService;
import com.candle.api.v1.domain.community.dto.request.CommunityRequest;
import com.candle.api.v1.domain.community.dto.response.CommunityResponse;
import com.candle.api.v1.domain.community.service.CommunityLikeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/community")
public class CommunityController {

    private final CommunityService communityService;
    private final CommunityLikeService communityLikeService;

    public CommunityController(CommunityService communityService, CommunityLikeService communityLikeService) {
        this.communityService = communityService;
        this.communityLikeService = communityLikeService;
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
    public Integer like(@RequestBody CommunityLikeRequest communityLikeRequest) {
        return communityLikeService.like(communityLikeRequest);
    }

    @DeleteMapping("/like")
    public Integer unlike(@RequestParam Integer likeId) {
        return communityLikeService.unlike(likeId);
    }
}
