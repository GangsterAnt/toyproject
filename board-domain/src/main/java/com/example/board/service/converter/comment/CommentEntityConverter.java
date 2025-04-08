package com.example.board.service.converter.comment;

import com.example.board.domain.Comment;
import com.example.board.model.CommentEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class CommentEntityConverter {

    public Comment convertFromEntity(CommentEntity commentEntity) {
        return Comment.builder()
                .commentId(commentEntity.getCommentId())
                .content(commentEntity.getContent())
                .ownerMemberId(commentEntity.getOwnerMemberId())
                .likes(commentEntity.getLikes())
                .dislikes(commentEntity.getDislikes())
                .createdAt(commentEntity.getCreatedAt())
                .rootPostId(commentEntity.getRootPostId())
                .parentCommentId(commentEntity.getParentCommentId())
                .build();
    }

    public List<Comment> convertFromEntityList(List<CommentEntity> commentEntityList) {
        return commentEntityList.stream()
                .filter(Objects::nonNull)
                .map(this::convertFromEntity)
                .collect(Collectors.toList());
    }

    public CommentEntity convertToEntity(Comment comment) {
        return CommentEntity.builder()
                .commentId(comment.getCommentId())
                .content(comment.getContent())
                .ownerMemberId(comment.getOwnerMemberId())
                .likes(comment.getLikes())
                .dislikes(comment.getDislikes())
                .createdAt(comment.getCreatedAt())
                .rootPostId(comment.getRootPostId())
                .parentCommentId(comment.getParentCommentId())
                .build();
    }

    public List<CommentEntity> convertToEntityList(List<Comment> commentList) {
        return commentList.stream()
                .filter(Objects::nonNull)
                .map(this::convertToEntity)
                .collect(Collectors.toList());
    }
}
