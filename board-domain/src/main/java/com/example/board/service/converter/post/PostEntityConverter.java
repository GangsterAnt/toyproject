package com.example.board.service.converter.post;

import com.example.board.bo.CommentBo;
import com.example.board.bo.PostBo;
import com.example.board.bo.PostSummaryBo;
import com.example.board.model.Post;
import com.example.board.service.CommentBoHierarchyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PostEntityConverter {

   private final CommentBoHierarchyService commentBoHierarchyService;

   //entity -> Bo
    public PostBo convertFromEntityWithComments(Post post, List<CommentBo> commentBoList) {
        return PostBo.builder()
                .postId(post.getPostId())
                .title(post.getTitle())
                .content(post.getContent())
                .ownerMemberId(post.getOwnerMemberId())
                .likes(post.getLikes())
                .dislikes(post.getDislikes())
                .createdAt(post.getCreatedAt())
                .updatedAt(post.getUpdatedAt())
                .deletedAt(post.getDeletedAt())
                .commentList(commentBoList)
                .build();
    }

    public Post convertFromBo(PostBo postBo) {
        return Post.builder()
                .postId(postBo.getPostId())
                .title(postBo.getTitle())
                .content(postBo.getContent())
                .ownerMemberId(postBo.getOwnerMemberId())
                .likes(postBo.getLikes())
                .dislikes(postBo.getDislikes())
                .createdAt(postBo.getCreatedAt())
                .updatedAt(postBo.getUpdatedAt())
                .deletedAt(postBo.getDeletedAt())
                .build();
    }

    public PostSummaryBo convertToPostSummaryBo(Post post) {
        return PostSummaryBo.builder()
                .postId(post.getPostId())
                .title(post.getTitle())
                .ownerMemberId(post.getOwnerMemberId())
                .likes(post.getLikes())
                .dislikes(post.getDislikes())
                .createdAt(post.getCreatedAt())
                .updatedAt(post.getUpdatedAt())
                .deletedAt(post.getDeletedAt())
                .build();
    }
}
