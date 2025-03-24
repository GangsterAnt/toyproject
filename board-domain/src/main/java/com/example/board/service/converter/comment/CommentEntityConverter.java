package com.example.board.service.converter.comment;

import com.example.board.bo.CommentBo;
import com.example.board.model.Comment;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class CommentEntityConverter {

    public CommentBo convertFromEntity(Comment comment) {
        return CommentBo.builder()
                .commentId(comment.getCommentId())
                .content(comment.getContent())
                .ownerMemberId(comment.getOwnerMemberId())
                .likes(comment.getLikes())
                .dislikes(comment.getDislikes())
                .createdAt(comment.getCreatedAt())
                .modifiedAt(comment.getModifiedAt())
                .rootPostId(comment.getRootPostId())
                .parentCommentId(comment.getParentCommentId())
                .build();
    }

    public List<CommentBo> convertFromEntityList(List<Comment> commentList) {
        return commentList.stream()
                .filter(Objects::nonNull)
                .map(this::convertFromEntity)
                .collect(Collectors.toList());
    }

    public Comment convertToEntity(CommentBo commentBo) {
        return Comment.builder()
                .commentId(commentBo.getCommentId())
                .content(commentBo.getContent())
                .ownerMemberId(commentBo.getOwnerMemberId())
                .likes(commentBo.getLikes())
                .dislikes(commentBo.getDislikes())
                .createdAt(commentBo.getCreatedAt())
                .modifiedAt(commentBo.getModifiedAt())
                .rootPostId(commentBo.getRootPostId())
                .parentCommentId(commentBo.getParentCommentId())
                .build();
    }

    public List<Comment> convertToEntityList(List<CommentBo> commentBoList) {
        return commentBoList.stream()
                .filter(Objects::nonNull)
                .map(this::convertToEntity)
                .collect(Collectors.toList());
    }
}
