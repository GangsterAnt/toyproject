package com.example.board.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class Comment {
    @Id
    private Long commentId;
    private String content;
    private Integer likes;
    private Integer dislikes;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private String ownerMemberId;
    private Long rootPostId;
    private Long childCommentId;
    private Long parentCommentId;
}
