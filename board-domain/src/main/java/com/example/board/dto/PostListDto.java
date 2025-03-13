package com.example.board.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class PostListDto {
    private List<PostSummaryDto> posts;
}
