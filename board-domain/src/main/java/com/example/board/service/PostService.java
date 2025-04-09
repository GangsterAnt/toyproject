package com.example.board.service;

import com.example.board.domain.BoardPageSizeEnum;
import com.example.board.domain.BoardPageSortEnum;
import com.example.board.domain.PageableWrapper;
import com.example.board.domain.Post;
import com.example.board.domain.PostSummary;
import com.example.board.dto.PostRequest;
import com.example.board.dto.PostResponse;
import com.example.board.repository.RepositoryWrapper;
import com.example.board.service.converter.comment.CommentDtoConverter;
import com.example.board.service.converter.post.PostDtoConverter;
import com.example.board.service.converter.post.PostRequestConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class PostService {

    private final RepositoryWrapper repositoryWrapper;
    private final PostDtoConverter postDtoConverter;
    private final CommentDtoConverter commentDtoConverter;
    private final PostRequestConverter postRequestConverter;

    public PostResponse getPostById(Long id) {
        Post post = repositoryWrapper.getPostById(id);
        return postDtoConverter.convertToDto(post);
    }

    public List<PostSummary> getAllPost(boolean filterDeleted, Long pageNumber) {
        PageableWrapper pageableWrapper = new PageableWrapper(pageNumber.intValue(), BoardPageSizeEnum.POST_DEFAULT_SIZE, BoardPageSortEnum.CREATED_DATE_ASC);
        return repositoryWrapper.getAllPosts(filterDeleted, pageableWrapper); //TODO create DTO
    }

    public Long createPost(PostRequest postRequest) {
        if (postRequest == null || postRequest.isInvalidRequest()) {
            log.error("Post ID should not be provided for a new post.");
            return null;
        }

        Post post = postRequestConverter.convertPostFromPostRequest(postRequest);
        return repositoryWrapper.createPost(post);
    }

    public boolean softDeletePost(Long id) {
        return repositoryWrapper.softDeletePost(id);
    }

    public boolean hidePost(Long id) {
        return repositoryWrapper.hidePost(id);
    }

    public boolean unHidePost(Long id) {
        return repositoryWrapper.unHidePost(id);
    }
}
