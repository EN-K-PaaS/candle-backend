package com.candle.api.v1.domain.community.service;

import com.candle.api.v1.domain.comment.dto.request.CommentDTO;
import com.candle.api.v1.domain.comment.entity.Comment;
import com.candle.api.v1.domain.community.entity.Community;
import com.candle.api.v1.domain.community.repository.CommunityRepository;
import com.candle.api.v1.domain.community.dto.request.CommunityRequest;
import com.candle.api.v1.domain.community.dto.response.CommunityResponse;
import com.candle.api.v1.domain.user.entity.User;
import com.candle.api.v1.domain.user.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CommunityService {

    private final CommunityRepository communityRepository;
    private final UserService userService;


    public CommunityService(CommunityRepository communityRepository, UserService userService) {
        this.communityRepository = communityRepository;
        this.userService = userService;
    }

    @Transactional
    public Integer deleteCommunity(Integer id) {
        if (!communityRepository.existsById(id)) {
            throw new IllegalArgumentException("해당 id에 대한 post가 존재하지 않습니다.");
        }

        communityRepository.deleteById(id);
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
            throw new IllegalArgumentException("해당 id에 대한 유저가 존재하지 않습니다.");
        }

        User user = userService.findById(userId);
        Community community = new Community(user, title, content, image, createdAt);
        communityRepository.save(community);
        return community.getId();
    }

    @Transactional(readOnly = true)
    public List<CommunityResponse> getCommunity() {
        List<Community> communities = communityRepository.findAll();
        List<CommunityResponse> communityResponseList = new ArrayList<>();
        List<CommentDTO> commentDTOList = new ArrayList<>();

        if (communities.isEmpty()) {
            throw new IllegalArgumentException("커뮤니티 게시글이 존재하지 않습니다.");
        }

        for(Community community : communities) {
            String userId = community.getUser().getId();
            String userName = community.getUser().getName();
            Integer communityId = community.getId();
            String title = community.getTitle();
            String content = community.getPost().getContent();
            String image = community.getPost().getImage();
            LocalDateTime createdAt = community.getPost().getCreatedAt();
            Integer likeCount = community.getLike().size();
            List<Comment> comments = community.getComments();

            for(Comment comment : comments) {
                commentDTOList.add(new CommentDTO(communityId, comment.getUser().getId(), comment.getPost().getContent(), comment.getPost().getImage()));
            }

            communityResponseList.add(new CommunityResponse(userId, userName, communityId, title, content, image, createdAt, likeCount, commentDTOList));
        }

        return communityResponseList;
    }

    @Transactional(readOnly = true)
    public Community findById(Integer communityId) {
        return communityRepository.findById(communityId).orElseThrow(() -> new IllegalArgumentException("해당 id에 대한 커뮤니티 게시글이 존재하지 않습니다."));
    }
}
