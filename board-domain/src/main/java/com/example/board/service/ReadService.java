package com.example.board.service;

import com.example.board.bo.BoardPageSizeEnum;
import com.example.board.bo.BoardPageSortEnum;
import com.example.board.bo.PageableWrapper;
import com.example.board.bo.PostBo;
import com.example.board.bo.PostSummaryBo;
import com.example.board.dto.CommentDto;
import com.example.board.dto.PostDto;
import com.example.board.repository.RepositoryWrapper;
import com.example.board.service.converter.comment.CommentDtoConverter;
import com.example.board.service.converter.post.PostDtoConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class ReadService {

    private final RepositoryWrapper repositoryWrapper;

    private final PostDtoConverter postDtoConverter;
    private final CommentDtoConverter commentDtoConverter;

    public PostDto getPostById(Long id) {
        PostBo postBo = repositoryWrapper.getPostById(id);
        return postDtoConverter.convertToDto(postBo);
    }

    public List<PostSummaryBo> getAllPost(boolean filterDeleted, Long pageNumber) {
        PageableWrapper pageableWrapper = new PageableWrapper(pageNumber.intValue(), BoardPageSizeEnum.POST_DEFAULT_SIZE, BoardPageSortEnum.CREATED_DATE_ASC);
        return repositoryWrapper.getAllPosts(filterDeleted, pageableWrapper); //TODO create DTO
    }

    public List<CommentDto> getChildComments(Long commentId, Long pageNumber) {
        PageableWrapper pageableWrapper = new PageableWrapper(pageNumber.intValue(), BoardPageSizeEnum.COMMENT_DEFAULT_SIZE, BoardPageSortEnum.CREATED_DATE_ASC);
        return commentDtoConverter.convertToDtoList(repositoryWrapper.getChildComments(commentId, pageableWrapper));
    }
}
