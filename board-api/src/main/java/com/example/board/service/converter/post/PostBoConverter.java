package com.example.board.service.converter.post;

import com.example.board.model.Post;
import com.example.board.service.CommentHierarchyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PostBoConverter {

   private final CommentHierarchyService commentHierarchyService;

   //entity -> Bo
    public PostBo convertFromEntityWithComments(Post post, List<CommentBo> commentBoList) {
        return PostBo.builder()
                .postId(post.getPostId())
                .title(post.getTitle())
                .content(post.getContent())
                .createdAt(post.getCreatedAt())
                .modifiedAt(post.getModifiedAt())
                .ownerMemberId(post.getOwnerMemberId())
                .likes(post.getLikes())
                .dislikes(post.getDislikes())
                .commentList(commentHierarchyService.assembleCommentListToHierarchy(commentBoList))
                .build();
    }
}
