package com.example.board.service;

import com.example.board.bo.PostBo;
import com.example.board.bo.PostSummaryBo;
import com.example.board.dto.PostDto;
import com.example.board.repository.RepositoryWrapper;
import com.example.board.service.converter.comment.CommentDtoConverter;
import com.example.board.service.converter.post.PostDtoConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class ReadService {

    private final RepositoryWrapper repositoryWrapper;

    private final PostDtoConverter postDtoConverter;
    private final CommentDtoConverter commentDtoConverter;

    public PostDto getPostById(Long id) {
        Pageable pageable = PageRequest.of(0, 20, Sort.by( "createdAt").ascending());
        PostBo postBo = repositoryWrapper.getPostById(id, pageable);
        return postDtoConverter.convertToDto(postBo);
    }

    public List<PostSummaryBo> getAllPost(boolean filterDeleted, Long pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber.intValue(), 20, Sort.by( "createdAt").ascending());
        return repositoryWrapper.getAllPosts(filterDeleted, pageable);
    }
}
