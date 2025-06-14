package com.example.board.service.post;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class PostSummary {
    private Long postId;
    private String title;
    private String ownerMemberId;
    private int likes;
    private int dislikes;
    private int commentCount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
