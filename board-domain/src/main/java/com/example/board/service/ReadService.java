package com.example.board.service;

import com.example.board.domain.BoardPageSizeEnum;
import com.example.board.domain.BoardPageSortEnum;
import com.example.board.domain.PageableWrapper;
import com.example.board.domain.Post;
import com.example.board.domain.PostSummary;
import com.example.board.dto.CommentDto;
import com.example.board.dto.PostResponse;
import com.example.board.repository.RepositoryWrapper;
import com.example.board.service.converter.comment.CommentDtoConverter;
import com.example.board.service.converter.post.PostDtoConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
@Deprecated
public class ReadService {

    private final RepositoryWrapper repositoryWrapper;

    private final PostDtoConverter postDtoConverter;
    private final CommentDtoConverter commentDtoConverter;

    public PostResponse getPostById(Long id) {
        Post post = repositoryWrapper.getPostById(id);
        return postDtoConverter.convertToDto(post);
    }

    public List<PostSummary> getAllPost(boolean filterDeleted, Long pageNumber) {
        PageableWrapper pageableWrapper = new PageableWrapper(pageNumber.intValue(), BoardPageSizeEnum.POST_DEFAULT_SIZE, BoardPageSortEnum.CREATED_DATE_ASC);
        return repositoryWrapper.getAllPosts(filterDeleted, pageableWrapper); //TODO create DTO
    }

    public List<CommentDto> getChildComments(Long commentId, Long pageNumber) {
        PageableWrapper pageableWrapper = new PageableWrapper(pageNumber.intValue(), BoardPageSizeEnum.COMMENT_DEFAULT_SIZE, BoardPageSortEnum.CREATED_DATE_ASC);
        return commentDtoConverter.convertToDtoList(repositoryWrapper.getChildComments(commentId, pageableWrapper));
    }
}
