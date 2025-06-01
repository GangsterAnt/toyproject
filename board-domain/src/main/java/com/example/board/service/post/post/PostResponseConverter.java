package com.example.board.service.post.post;

import com.example.board.service.post.Post;
import com.example.board.service.post.PostResponse;
import com.example.board.service.comment.comment.CommentDtoConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/*
    * This class is responsible for converting PostBo <-> PostDto
 */
@Component
@RequiredArgsConstructor
public class PostResponseConverter {

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
}
