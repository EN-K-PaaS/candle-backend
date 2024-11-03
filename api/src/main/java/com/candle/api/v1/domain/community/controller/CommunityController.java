package com.candle.api.v1.domain.community.controller;

import com.candle.api.v1.domain.community.dto.request.CommunityLikeRequest;
import com.candle.api.v1.domain.community.service.CommunityService;
import com.candle.api.v1.domain.community.dto.request.CommunityRequest;
import com.candle.api.v1.domain.community.dto.response.CommunityResponse;
import com.candle.api.v1.domain.community.service.CommunityLikeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/communities")
@Tag(name = "Community", description = "커뮤니티 관련 API")
public class CommunityController {

    private final CommunityService communityService;
    private final CommunityLikeService communityLikeService;

    public CommunityController(CommunityService communityService, CommunityLikeService communityLikeService) {
        this.communityService = communityService;
        this.communityLikeService = communityLikeService;
    }

    @Operation(summary = "커뮤니티 작성", description = "커뮤니티를 작성합니다.")
    @PostMapping
    public Integer writingCommunity(@RequestBody CommunityRequest communityRequest) {
        return communityService.writingCommunity(communityRequest);
    }

    @Operation(summary = "커뮤니티 조회", description = "커뮤니티를 조회합니다.")
    @GetMapping
    public List<CommunityResponse> getCommunity() {
        return communityService.getCommunity();
    }

    @Operation(summary = "커뮤니티 수정", description = "커뮤니티를 수정합니다.")
    @DeleteMapping("/{id}")
    public Integer deleteCommunity(@PathVariable Integer id) {
        return communityService.deleteCommunity(id);
    }

    @Operation(summary = "커뮤니티 좋아요", description = "커뮤니티에 좋아요를 누릅니다.")
    @PostMapping("/like")
    public Integer like(@RequestBody CommunityLikeRequest communityLikeRequest) {
        return communityLikeService.like(communityLikeRequest);
    }

    @Operation(summary = "커뮤니티 좋아요 취소", description = "커뮤니티에 좋아요를 취소합니다.")
    @DeleteMapping("/like/{id}")
    public Integer unlike(@PathVariable Integer id) {
        return communityLikeService.unlike(id);
    }
}
