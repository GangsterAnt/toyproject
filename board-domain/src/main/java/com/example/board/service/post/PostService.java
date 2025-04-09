package com.example.board.service.post;

import com.example.board.domain.BoardPageSizeEnum;
import com.example.board.domain.BoardPageSortEnum;
import com.example.board.domain.PageableWrapper;
import com.example.board.domain.Post;
import com.example.board.domain.PostSummary;
import com.example.board.dto.PostRequest;
import com.example.board.dto.PostResponse;
import com.example.board.service.converter.post.PostResponseConverter;
import com.example.board.service.converter.post.PostRequestConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class PostService {

    private final PostResponseConverter postResponseConverter;
    private final PostRequestConverter postRequestConverter;
    private final PostQueryService postQueryService;

    public PostResponse getPostById(Long id) {
        Post post = postQueryService.getPostById(id);
        return postResponseConverter.convertToDto(post);
    }

    public List<PostSummary> getAllPost(boolean filterDeleted, Long pageNumber) {
        PageableWrapper pageableWrapper = new PageableWrapper(pageNumber.intValue(), BoardPageSizeEnum.POST_DEFAULT_SIZE, BoardPageSortEnum.CREATED_DATE_ASC);
        return postQueryService.getAllPosts(filterDeleted, pageableWrapper); //TODO create DTO
    }

    public Long createPost(PostRequest postRequest) {
        if (postRequest == null || postRequest.isInvalidRequest()) {
            log.error("Post ID should not be provided for a new post.");
            return null;
        }

        Post post = postRequestConverter.convertPostFromPostRequest(postRequest);
        return postQueryService.createPost(post);
    }

    public boolean softDeletePost(Long id) {
        return postQueryService.softDeletePost(id);
    }

    public boolean hidePost(Long id) {
        return postQueryService.hidePost(id);
    }

    public boolean unHidePost(Long id) {
        return postQueryService.unHidePost(id);
    }
}
