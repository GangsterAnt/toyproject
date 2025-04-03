package com.example.board.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
public class PostDto {
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

    public boolean isValidNewPost() {
        return this.title != null && !this.title.isEmpty()
                && this.content != null && !this.content.isEmpty()
                && this.ownerMemberId != null && !this.ownerMemberId.isEmpty()
                && postId == null
                && dislikes == null
                && likes == null
                && createdAt == null
                && updatedAt == null
                && deletedAt == null;
    }

    public boolean isInvalidNewPost() {
        return !isValidNewPost();
    }
}
