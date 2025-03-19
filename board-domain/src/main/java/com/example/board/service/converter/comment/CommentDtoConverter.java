package com.example.board.service.converter.comment;

import com.example.board.dto.CommentDto;
import com.example.board.model.Comment;
import org.springframework.stereotype.Component;

@Component
public class CommentDtoConverter {

    public Comment convertToEntity(CommentDto commentDto) {
        return Comment.builder()
                .commentId(commentDto.getCommentId())
                .content(commentDto.getContent())
                .createdAt(commentDto.getCreatedAt())
                .modifiedAt(commentDto.getModifiedAt())
                .ownerMemberId(commentDto.getOwnerMemberId())
                .rootPostId(commentDto.getRootPostId())
                .likes(commentDto.getLikes())
                .dislikes(commentDto.getDislikes())
                .build();
    }

    public CommentDto convertToDto(Comment commentBo) {
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
