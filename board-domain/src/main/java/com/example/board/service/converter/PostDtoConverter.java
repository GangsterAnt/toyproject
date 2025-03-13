package com.example.board.service.converter;

import com.example.board.dto.PostDto;
import com.example.board.model.PostBo;
import org.springframework.stereotype.Component;

@Component
public class PostDtoConverter {

    public PostDto convertToDto(PostBo post) {

        return PostDto.builder()
                .postId(post.getPostId())
                .title(post.getTitle())
                .title(post.getContent())
                .author(post.getOwnerMemberId())
                .likes(post.getLikes())
                .dislikes(post.getDislikes())
                .createdAt(post.getCreatedAt())
                .modifiedAt(post.getModifiedAt())
                .build();
    }

    public PostBo convertToEntity(PostDto postDto) {
        return null;
    }
}
