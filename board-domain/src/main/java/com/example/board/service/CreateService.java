package com.example.board.service;

import com.example.board.bo.PostBo;
import com.example.board.dto.PostDto;
import com.example.board.repository.RepositoryWrapper;
import com.example.board.service.converter.post.PostDtoConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class CreateService {

    private final RepositoryWrapper repositoryWrapper;
    private final PostDtoConverter postDtoConverter;

    public boolean createPost(PostDto newPost) {
        if (newPost == null || newPost.isInvalidNewPost()) {
            log.error("Post ID should not be provided for a new post.");
            return false;
        }

        PostBo postBo = postDtoConverter.convertToBoFromNewPost(newPost);
        return repositoryWrapper.createPost(postBo);
    }
}
