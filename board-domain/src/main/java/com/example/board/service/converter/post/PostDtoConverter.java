package com.example.board.service.converter.post;

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

    public PostBo convertToBo(PostDto postDto) {
        return PostBo.builder()
                .postId(postDto.getPostId())
                .title(postDto.getTitle())
                .content(postDto.getContent())
                .ownerMemberId(postDto.getAuthor())
                .likes(postDto.getLikes())
                .dislikes(postDto.getDislikes())
                .createdAt(postDto.getCreatedAt())
                .modifiedAt(postDto.getModifiedAt())
                .build();
    }
}
