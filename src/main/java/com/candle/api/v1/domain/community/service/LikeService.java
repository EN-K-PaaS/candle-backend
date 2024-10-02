package com.candle.api.v1.domain.community.service;

import com.candle.api.v1.domain.community.dto.request.LikeRequest;
import com.candle.api.v1.domain.community.entity.Community;
import com.candle.api.v1.domain.community.entity.Like;
import com.candle.api.v1.domain.community.repository.LikeRepository;
import com.candle.api.v1.domain.user.entity.User;
import com.candle.api.v1.domain.user.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LikeService {
    private final LikeRepository likeRepository;
    private final CommunityService communityService;
    private final UserService userService;

    public LikeService(LikeRepository likeRepository, CommunityService communityService, UserService userService) {
        this.likeRepository = likeRepository;
        this.communityService = communityService;
        this.userService = userService;
    }

    @Transactional
    public Integer like(LikeRequest likeRequest) {
        Integer communityId = likeRequest.communityId();
        String userId = likeRequest.userId();
        Community community = communityService.findById(communityId);
        User user = userService.findById(userId);

        if (likeRepository.existsByCommunityIdAndUserId(communityId, userId)) {
            throw new IllegalArgumentException("이미 좋아요를 누른 게시글입니다.");
        }

        Like like = new Like(community, user);

        likeRepository.save(like);
        return like.getId();
    }

    @Transactional
    public Integer unlike(Integer likeId) {
        if (!likeRepository.existsById(likeId)) {
            throw new IllegalArgumentException("해당 id에 대한 좋아요가 존재하지 않습니다.");
        }

        Like like = likeRepository.findById(likeId).get();

        likeRepository.delete(like);
        return like.getId();
    }
}
