package com.example.board.service.comment;

import com.example.board.domain.BoardPageSizeEnum;
import com.example.board.domain.BoardPageSortEnum;
import com.example.board.domain.PageableWrapper;
import com.example.board.service.comment.comment.CommentDtoConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class CommentService {
    private final CommentQueryService commentQueryService;
    private final CommentDtoConverter commentDtoConverter;

    public List<CommentDto> getChildComments(Long commentId, Long pageNumber) {
        PageableWrapper pageableWrapper = new PageableWrapper(pageNumber.intValue(), BoardPageSizeEnum.COMMENT_DEFAULT_SIZE, BoardPageSortEnum.CREATED_DATE_ASC);
        return commentDtoConverter.convertToDtoList(commentQueryService.getChildComments(commentId, pageableWrapper));
    }

    public boolean hideComment(Long id) {
        return commentQueryService.hideComment(id);
    }

    public boolean unHideComment(Long id) {
        try {
            return commentQueryService.unHideComment(id);
        }catch (Exception e) {
            return false;
        }
    }
}
