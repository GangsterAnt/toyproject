package com.example.board.service.comment;

import com.example.board.domain.Comment;
import com.example.board.domain.PageableWrapper;
import com.example.board.repository.CommentRepository;
import com.example.board.service.converter.comment.CommentEntityConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
@Slf4j
public class CommentQueryService {

    private final CommentRepository commentRepository;
    private final CommentEntityConverter commentEntityConverter;

    public List<Comment> getRootCommentListByPostId(Long id, PageableWrapper pageableWrapper) {
        return Optional.ofNullable(commentRepository.findActiveRootCommentsByRootPostId(id, pageableWrapper.getPageable()))
                .stream()
                .flatMap(List::stream)
                .map(commentEntityConverter::convertFromEntity)
                .collect(Collectors.toList());
    }

    public List<Comment> getChildCommentsListByParentCommentId(Long id, Pageable pageable) {
        return Optional.ofNullable(commentRepository.findActiveChildCommentsByParentCommentId(id, pageable))
                .stream()
                .flatMap(List::stream)
                .map(commentEntityConverter::convertFromEntity)
                .collect(Collectors.toList());
    }

    public List<Comment> getChildComments(Long commentId, PageableWrapper pageableWrapper) {
        return Optional.ofNullable(commentRepository.findActiveChildCommentsByParentCommentId(commentId, pageableWrapper.getPageable()))
                .stream()
                .flatMap(List::stream)
                .map(commentEntityConverter::convertFromEntity)
                .collect(Collectors.toList());
    }

    public boolean hideComment(Long id) {
        return commentRepository.hideCommentById(id);
    }

    public boolean unHideComment(Long id) {
        return commentRepository.unHideCommentById(id);
    }


}
