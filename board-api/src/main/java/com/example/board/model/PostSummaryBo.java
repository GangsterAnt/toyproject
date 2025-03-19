package com.example.board.model;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class PostSummaryBo {
    private Long postId;
    private String title;
    private String ownerMemberId;
    private int likes;
    private int dislikes;
    private int commentCount;
    private LocalDateTime createdAt;
}
