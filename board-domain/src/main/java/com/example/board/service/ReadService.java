package com.example.board.service;

import com.example.board.bo.PostBo;
import com.example.board.dto.PostDto;
import com.example.board.repository.RepositoryWrapper;
import com.example.board.service.converter.comment.CommentDtoConverter;
import com.example.board.service.converter.post.PostDtoConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

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
}
