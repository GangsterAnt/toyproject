package com.example.board.service.converter.comment;

import com.example.board.dto.CommentDto;
import com.example.board.model.Comment;
import com.example.board.model.CommentBo;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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

    public List<CommentBo> convertFromEntityList(List<Comment> commentList) {
        return commentList.stream()
                .filter(Objects::nonNull)
                .map(this::convertFromEntity)
                .collect(Collectors.toList());
    }

    public CommentDto convertToDto(CommentBo commentBo) {
        return CommentDto.builder()
                .commentId(commentBo.getCommentId())
                .content(commentBo.getContent())
                .createdAt(commentBo.getCreatedAt())
                .modifiedAt(commentBo.getModifiedAt())
                .ownerMemberId(commentBo.getOwnerMemberId())
                .rootPostId(commentBo.getRootPostId())
                .likes(commentBo.getLikes())
                .dislikes(commentBo.getDislikes())
                .build();
    }
}
