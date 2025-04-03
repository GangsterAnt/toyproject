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
                .commentList(commentBoList)
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
                .build();
    }
}
