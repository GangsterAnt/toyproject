package com.example.board.service.converter.post;

import com.example.board.dto.PostDto;
import com.example.board.model.Post;
import org.springframework.stereotype.Component;

@Component
public class PostDtoConverter {

    public PostDto convertToDto(Post post) {
        return PostDto.builder()
                .postId(post.getPostId())
                .title(post.getTitle())
                .title(post.getContent())
                .ownerMemberId(post.getOwnerMemberId())
                .likes(post.getLikes())
                .dislikes(post.getDislikes())
                .createdAt(post.getCreatedAt())
                .modifiedAt(post.getModifiedAt())
                .build();
    }

    public PostDto convertToDto(PostBo post) {
        return PostDto.builder()
                .postId(post.getPostId())
                .title(post.getTitle())
                .title(post.getContent())
                .ownerMemberId(post.getOwnerMemberId())
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
                .ownerMemberId(postDto.getOwnerMemberId())
                .likes(postDto.getLikes())
                .dislikes(postDto.getDislikes())
                .createdAt(postDto.getCreatedAt())
                .modifiedAt(postDto.getModifiedAt())
                .build();
    }
}
