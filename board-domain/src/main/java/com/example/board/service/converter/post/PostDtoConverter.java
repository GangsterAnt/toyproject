package com.example.board.service.converter.post;

import com.example.board.bo.PostBo;
import com.example.board.dto.PostDto;
import com.example.board.model.Post;
import com.example.board.service.converter.comment.CommentDtoConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

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
                .updatedAt(post.getUpdatedAt())
                .deletedAt(post.getDeletedAt()) //Is this necessary?
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
                .updatedAt(post.getUpdatedAt())
                .deletedAt(post.getDeletedAt())
                .build();
    }

    public PostBo convertToBoFromNewPost(PostDto newPost) {
        return PostBo.builder()
                .title(newPost.getTitle())
                .content(newPost.getContent())
                .ownerMemberId(newPost.getOwnerMemberId())
                .createdAt(ZonedDateTime.now(ZoneId.of("UTC")).toLocalDateTime())
                .updatedAt(ZonedDateTime.now(ZoneId.of("UTC")).toLocalDateTime())
                .deletedAt(null)
                .likes(0)
                .dislikes(0)
                .commentList(null)
                .build();
    }
}
