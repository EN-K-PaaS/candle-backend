package com.candle.api.v1.domain.community.controller;

import com.candle.api.v1.domain.community.service.CommunityService;
import com.candle.api.v1.domain.community.dto.request.CommunityRequest;
import com.candle.api.v1.domain.community.dto.response.CommunityResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/community")
public class CommunityController {

    private final CommunityService communityService;

    public CommunityController(CommunityService communityService) {
        this.communityService = communityService;
    }

    @PostMapping("/community")
    public Integer writingCommunity(@RequestBody CommunityRequest communityRequest) {
        return communityService.writingCommunity(communityRequest);
    }

    @GetMapping("/community")
    public List<CommunityResponse> getCommunity() {
        return communityService.getCommunity();
    }

    @DeleteMapping("/community")
    public Integer deleteCommunity(@RequestParam Integer id) {
        return communityService.deleteCommunity(id);
    }
}
