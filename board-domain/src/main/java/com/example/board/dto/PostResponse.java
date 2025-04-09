package com.example.board.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
public class PostResponse {
    private Long postId;
    private String title;
    private String content;
    private String ownerMemberId;

    private Integer likes;
    private Integer dislikes;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;


    private List<CommentDto> commentList;
}
