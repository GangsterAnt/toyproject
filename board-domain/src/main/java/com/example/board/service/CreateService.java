package com.example.board.service;

import com.example.board.domain.Post;
import com.example.board.dto.PostResponse;
import com.example.board.repository.RepositoryWrapper;
import com.example.board.service.converter.post.PostDtoConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
@Deprecated
public class CreateService {

    private final RepositoryWrapper repositoryWrapper;
    private final PostDtoConverter postDtoConverter;

    public Long createPost(PostResponse newPost) {
        if (newPost == null || newPost.isInvalidNewPost()) {
            log.error("Post ID should not be provided for a new post.");
            return null;
        }

        Post post = postDtoConverter.convertToBoFromNewPost(newPost);
        return repositoryWrapper.createPost(post);
    }
}
