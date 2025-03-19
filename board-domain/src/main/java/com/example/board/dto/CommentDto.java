package com.example.board.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Builder
@Getter
public class CommentDto {

    private Long commentId;
    private String content;
    private String ownerMemberId;

    private Integer likes;
    private Integer dislikes;

    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    private Long rootPostId;
    private Long childCommentId;
    private Long parentCommentId;
}
