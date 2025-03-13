package com.example.board.service;

import com.example.board.dto.PostListDto;
import com.example.board.model.CommentBo;
import com.example.board.model.Post;
import com.example.board.model.PostBo;
import com.example.board.repository.CommentRepository;
import com.example.board.repository.PostRepository;
import com.example.board.service.converter.CommentBoConverter;
import com.example.board.service.converter.PostBoConverter;
import com.example.board.service.converter.PostDtoConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ReadService {

    private final PostDtoConverter postDtoConverter;

    private final PostBoConverter postBoConverter;
    private final CommentBoConverter commentBoConverter;

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    public PostBo getPostById(Long id) {
        Post post = postRepository.getReferenceById(id);
        if (post == null) {
            return null; //TODO return 404
        }

        List<CommentBo> collect = Optional.ofNullable(commentRepository.findByRootPostId(id))
                .stream()
                .flatMap(List::stream)
                .map(commentBoConverter::convertFromEntity)
                .collect(Collectors.toList());

        return postBoConverter.convertFromEntity(post, collect);
    }

    public PostListDto getPostList() {
        return null;
    }
}
