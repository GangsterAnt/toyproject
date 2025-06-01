package com.example.board.service.comment.comment;

import com.example.board.service.comment.Comment;
import com.example.board.service.comment.CommentDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/*
    * This class is responsible for converting CommentDto <-> CommentBo
 */
@Component
public class CommentDtoConverter {

    public Comment convertToBo(CommentDto commentDto) {
        if (commentDto == null) {
            return null;
        }

        return Comment.builder()
                .commentId(commentDto.getCommentId())
                .content(commentDto.getContent())
                .createdAt(commentDto.getCreatedAt())
                .modifiedAt(commentDto.getModifiedAt())
                .ownerMemberId(commentDto.getOwnerMemberId())
                .rootPostId(commentDto.getRootPostId())
                .likes(commentDto.getLikes())
                .dislikes(commentDto.getDislikes())
                .parentCommentId(commentDto.getParentCommentId())
                .childCommentList(Optional.ofNullable(commentDto.getChildCommentDtoList())
                        .stream()
                        .flatMap(List::stream)
                        .map(this::convertToBo) //Caution: recursive call
                        .collect(Collectors.toList()))
                .build();
    }

    public CommentDto convertToDto(Comment comment) {
        if (comment == null) {
            return null;
        }

        return CommentDto.builder()
                .commentId(comment.getCommentId())
                .content(comment.getContent())
                .createdAt(comment.getCreatedAt())
                .modifiedAt(comment.getModifiedAt())
                .ownerMemberId(comment.getOwnerMemberId())
                .rootPostId(comment.getRootPostId())
                .likes(comment.getLikes())
                .dislikes(comment.getDislikes())
                .parentCommentId(comment.getParentCommentId())
                .childCommentDtoList(Optional.ofNullable(comment.getChildCommentList())
                        .stream()
                        .flatMap(List::stream)
                        .map(this::convertToDto) //Caution: recursive call
                        .collect(Collectors.toList()))
//                .getChildCommentCount(Optional.ofNullable(comment.getChildCommentList())
//                        .stream()
//                        .flatMap(List::stream)
//                        .map(this::convertToDto) //Caution: recursive call
//                        .collect(Collectors.toList()).size())
                .build();
    }

    public List<CommentDto> convertToDtoList(List<Comment> commentList) {
        return commentList.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
}
