package com.example.board.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
@Table(name = "post_v0")
public class Post {
    @Id
    private Long postId;
    private String title;
    private String content;
    private String ownerMemberId;

    private int likes;
    private int dislikes;

    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}
