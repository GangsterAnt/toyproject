package com.example.board.service.converter;

import com.example.board.model.Comment;
import com.example.board.model.CommentBo;
import org.springframework.stereotype.Component;

@Component
public class CommentBoConverter {

    public CommentBo convertFromEntity(Comment comment) {
        return CommentBo.builder()
                .commentId(comment.getCommentId())
                .content(comment.getContent())
                .createdAt(comment.getCreatedAt())
                .modifiedAt(comment.getModifiedAt())
                .ownerMemberId(comment.getOwnerMemberId())
                .rootPostId(comment.getRootPostId())
                .likes(comment.getLikes())
                .dislikes(comment.getDislikes())
                .build();

    }
}
