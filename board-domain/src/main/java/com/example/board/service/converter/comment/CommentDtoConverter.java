package com.example.board.service.converter.comment;

import com.example.board.bo.CommentBo;
import com.example.board.dto.CommentDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/*
    * This class is responsible for converting CommentDto <-> CommentBo
 */
@Component
public class CommentDtoConverter {

    public CommentBo convertToBo(CommentDto commentDto) {
        if (commentDto == null) {
            return null;
        }

        CommentBo childCommentBo = convertToBo(commentDto.getChildCommentDto());
        CommentBo parentCommentBo = convertToBo(commentDto.getParentCommentDto());

        return CommentBo.builder()
                .commentId(commentDto.getCommentId())
                .content(commentDto.getContent())
                .createdAt(commentDto.getCreatedAt())
                .modifiedAt(commentDto.getModifiedAt())
                .ownerMemberId(commentDto.getOwnerMemberId())
                .rootPostId(commentDto.getRootPostId())
                .likes(commentDto.getLikes())
                .dislikes(commentDto.getDislikes())
                .childCommentBo(childCommentBo)
                .parentCommentBo(parentCommentBo)
                .build();
    }

    public CommentDto convertToDto(CommentBo commentBo) {
        if (commentBo == null) {
            return null;
        }

        CommentDto childCommentDto = convertToDto(commentBo.getChildCommentBo());
        CommentDto parentCommentDto = convertToDto(commentBo.getParentCommentBo());

        return CommentDto.builder()
                .commentId(commentBo.getCommentId())
                .content(commentBo.getContent())
                .createdAt(commentBo.getCreatedAt())
                .modifiedAt(commentBo.getModifiedAt())
                .ownerMemberId(commentBo.getOwnerMemberId())
                .rootPostId(commentBo.getRootPostId())
                .likes(commentBo.getLikes())
                .dislikes(commentBo.getDislikes())
                .childCommentDto(childCommentDto)
                .parentCommentDto(parentCommentDto)
                .build();
    }

    public List<CommentDto> convertToDtoList(List<CommentBo> commentBoList) {
        return commentBoList.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
}
