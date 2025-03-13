package com.example.board.model;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class Post {
    private Long postId;
    private String title;
    private String content;
    private String ownerMemberId;

    private int likes;
    private int dislikes;

    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}
