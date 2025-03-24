package com.example.board.service.converter.comment;

import com.example.board.bo.CommentBo;
import com.example.board.dto.CommentDto;
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
                .childCommentId(comment.getChildCommentId())
                .build();
    }

    public List<CommentBo> convertFromEntityList(List<Comment> commentList) {
        return commentList.stream()
                .filter(Objects::nonNull)
                .map(this::convertFromEntity)
                .collect(Collectors.toList());
    }

    public Comment convertToDto(CommentBo commentBo) {
        return Comment.builder()
                .commentId(commentBo.getCommentId())
                .content(commentBo.getContent())
                .ownerMemberId(commentBo.getOwnerMemberId())
                .likes(commentBo.getLikes())
                .dislikes(commentBo.getDislikes())
                .createdAt(commentBo.getCreatedAt())
                .modifiedAt(commentBo.getModifiedAt())
                .rootPostId(commentBo.getRootPostId())
                .childCommentId(commentBo.getChildCommentId())
                .parentCommentId(commentBo.getParentCommentId())
                .build();
    }

    public List<Comment> convertToDtoList(List<CommentBo> commentBoList) {
        return commentBoList.stream()
                .filter(Objects::nonNull)
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
}
