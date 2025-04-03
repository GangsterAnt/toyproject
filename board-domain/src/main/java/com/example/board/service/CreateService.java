package com.example.board.service;

import com.example.board.bo.PostBo;
import com.example.board.dto.PostDto;
import com.example.board.repository.RepositoryWrapper;
import com.example.board.service.converter.post.PostDtoConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateService {

    private final RepositoryWrapper repositoryWrapper;
    private final PostDtoConverter postDtoConverter;

    public boolean createPost(PostDto newPost) {
        PostBo postBo = postDtoConverter.convertToBoFromNewPost(newPost);
        return repositoryWrapper.createPost(postBo);
    }
}
