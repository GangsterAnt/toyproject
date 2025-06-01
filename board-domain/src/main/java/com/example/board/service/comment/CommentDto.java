package com.example.board.service.comment;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
public class CommentDto {

    private Long commentId;
    private String content;
    private String ownerMemberId;

    private Integer likes;
    private Integer dislikes;

    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    private Long rootPostId;

    private Long parentCommentId;
    private List<CommentDto> childCommentDtoList;
}
