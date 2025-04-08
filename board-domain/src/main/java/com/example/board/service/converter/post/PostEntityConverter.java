package com.example.board.service.converter.post;

import com.example.board.domain.Comment;
import com.example.board.domain.Post;
import com.example.board.domain.PostSummary;
import com.example.board.model.PostEntity;
import com.example.board.service.CommentBoHierarchyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PostEntityConverter {

   private final CommentBoHierarchyService commentBoHierarchyService;

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

    public PostEntity convertFromBo(Post post) {
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

    public PostSummary convertToPostSummaryBo(PostEntity postEntity) {
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
