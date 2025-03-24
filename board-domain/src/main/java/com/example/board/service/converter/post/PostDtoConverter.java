package com.example.board.service.converter.post;

import com.example.board.bo.PostBo;
import com.example.board.dto.PostDto;
import com.example.board.model.Post;
import com.example.board.service.converter.comment.CommentDtoConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/*
    * This class is responsible for converting PostBo <-> PostDto
 */
@Component
@RequiredArgsConstructor
public class PostDtoConverter {

    private final CommentDtoConverter commentDtoConverter;

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
                .commentList(commentDtoConverter.convertToDtoList(post.getCommentList()))
                .build();
    }

    public PostBo convertToBo(PostDto post) {
        return PostBo.builder()
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
}
