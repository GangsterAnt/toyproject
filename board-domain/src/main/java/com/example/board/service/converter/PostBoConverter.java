package com.example.board.service.converter;

import com.example.board.model.CommentBo;
import com.example.board.model.Post;
import com.example.board.model.PostBo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PostBoConverter {

   private final CommentHierarchyService commentHierarchyService;

    public PostBo convertFromEntity(Post post, List<CommentBo> commentBoList) {

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
