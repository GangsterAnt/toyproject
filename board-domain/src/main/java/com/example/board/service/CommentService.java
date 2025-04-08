package com.example.board.service;

import com.example.board.domain.BoardPageSizeEnum;
import com.example.board.domain.BoardPageSortEnum;
import com.example.board.domain.PageableWrapper;
import com.example.board.dto.CommentDto;
import com.example.board.repository.RepositoryWrapper;
import com.example.board.service.converter.comment.CommentDtoConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class CommentService {

    private final RepositoryWrapper repositoryWrapper;
    private final CommentDtoConverter commentDtoConverter;

    public List<CommentDto> getChildComments(Long commentId, Long pageNumber) {
        PageableWrapper pageableWrapper = new PageableWrapper(pageNumber.intValue(), BoardPageSizeEnum.COMMENT_DEFAULT_SIZE, BoardPageSortEnum.CREATED_DATE_ASC);
        return commentDtoConverter.convertToDtoList(repositoryWrapper.getChildComments(commentId, pageableWrapper));
    }

    public boolean hideComment(Long id) {
        return repositoryWrapper.hideComment(id);
    }

    public boolean unHideComment(Long id) {
        try {
            return repositoryWrapper.unHideComment(id);
        }catch (Exception e) {
            return false;
        }
    }
}
