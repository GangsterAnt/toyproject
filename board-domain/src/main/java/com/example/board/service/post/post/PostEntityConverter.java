package com.example.board.service.post.post;

import com.example.board.service.comment.Comment;
import com.example.board.service.post.Post;
import com.example.board.service.post.PostSummary;
import com.example.board.service.post.PostEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PostEntityConverter {

   //entity -> Bo
    public Post convertFromEntityWithComments(PostEntity postEntity, List<Comment> commentList) {
        return Post.builder()
                .postId(postEntity.getPostId())
                .title(postEntity.getTitle())
                .content(postEntity.getContent())
                .ownerMemberId(postEntity.getOwnerMemberId())
                .likes(postEntity.getLikes())
                .dislikes(postEntity.getDislikes())
                .createdAt(postEntity.getCreatedAt())
                .updatedAt(postEntity.getUpdatedAt())
                .deletedAt(postEntity.getDeletedAt())
                .commentList(commentList)
                .build();
    }

    public PostEntity convertFromDomainModel(Post post) {
        return PostEntity.builder()
                .postId(post.getPostId())
                .title(post.getTitle())
                .content(post.getContent())
                .ownerMemberId(post.getOwnerMemberId())
                .likes(post.getLikes())
                .dislikes(post.getDislikes())
                .createdAt(post.getCreatedAt())
                .updatedAt(post.getUpdatedAt())
                .deletedAt(post.getDeletedAt())
                .build();
    }

    public PostSummary convertToPostSummary(PostEntity postEntity) {
        return PostSummary.builder()
                .postId(postEntity.getPostId())
                .title(postEntity.getTitle())
                .ownerMemberId(postEntity.getOwnerMemberId())
                .likes(postEntity.getLikes())
                .dislikes(postEntity.getDislikes())
                .createdAt(postEntity.getCreatedAt())
                .updatedAt(postEntity.getUpdatedAt())
                .deletedAt(postEntity.getDeletedAt())
                .build();
    }
}
