package com.example.board.service;

import com.example.board.dto.PostDto;
import com.example.board.repository.RepositoryWrapper;
import com.example.board.service.converter.post.PostDtoConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ReadService {

//    private final RepositoryWrapper repositoryWrapper;
//
//    private final PostDtoConverter postDtoConverter;
//    private final PostSummaryDtoConverter postSummaryDtoConverter;
//    private final PostListDtoConverter postListDtoConverter;
//
//    public PostDto getPostById(Long id) {
//        PostBo postById = repositoryWrapper.getPostById(id);
//        return postDtoConverter.convertToDto(postById);
//    }
//
//    public PostListDto getPostList() {
//        List<PostSummaryBo> postSummaryList = repositoryWrapper.getPostSummaryList();
//        List<PostSummaryDto> postSummaryDtos = postSummaryDtoConverter.convertToDtoList(postSummaryList);
//        return postListDtoConverter.convertToDto(postSummaryDtos);
//    }
}
