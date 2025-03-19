package com.example.board.model;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
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
