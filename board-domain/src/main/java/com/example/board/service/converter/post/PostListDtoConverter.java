package com.example.board.service.converter.post;

import com.example.board.dto.PostListDto;
import com.example.board.dto.PostSummaryDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PostListDtoConverter {

    public PostListDto convertToDto(List<PostSummaryDto> posts) {
        return PostListDto.builder()
                .posts(posts)
                .build();
    }
}
