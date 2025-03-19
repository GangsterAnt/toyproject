package com.example.board.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
public class PostDto {
    private Long postId;
    private String title;
    private String content;
    private String ownerMemberId;
    private Integer likes;
    private Integer dislikes;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private List<CommentDto> comments;
}
