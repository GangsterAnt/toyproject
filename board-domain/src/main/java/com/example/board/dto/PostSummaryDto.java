package com.example.board.dto;

/*
글 목록을 위해 사용되는 각 글의 요약 정보를 담는 DTO 클래스
 */

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;


@Getter
@Builder
public class PostSummaryDto {
    private Long id;
    private String title;
    private String author;
    private int likes;
    private int dislikes;
    private int commentCount;
    private LocalDateTime createdAt;
}
