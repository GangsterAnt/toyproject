package com.example.board.service.converter.post;

import com.example.board.domain.Post;
import com.example.board.dto.PostResponse;
import com.example.board.service.converter.comment.CommentDtoConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.time.ZonedDateTime;

/*
    * This class is responsible for converting PostBo <-> PostDto
 */
@Component
@RequiredArgsConstructor
public class PostDtoConverter {

    private final CommentDtoConverter commentDtoConverter;

    public PostResponse convertToDto(Post post) {
        return PostResponse.builder()
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

    public Post convertToBo(PostResponse post) {
        return Post.builder()
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

    public Post convertToBoFromNewPost(PostResponse newPost) {
        return Post.builder()
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
