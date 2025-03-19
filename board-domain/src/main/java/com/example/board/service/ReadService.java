package com.example.board.service;

import com.example.board.dto.CommentDto;
import com.example.board.dto.PostDto;
import com.example.board.model.Comment;
import com.example.board.model.Post;
import com.example.board.repository.CommentRepository;
import com.example.board.repository.PostRepository;
import com.example.board.service.converter.comment.CommentDtoConverter;
import com.example.board.service.converter.post.PostDtoConverter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class ReadService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    private final PostDtoConverter dtoConverter;
    private final CommentDtoConverter commentDtoConverter;

    public PostDto getPostById(Long id) {
        Optional<Post> byPostId = postRepository.findByPostId(id);
        if (byPostId.isEmpty()) {
            return null; // TODO return 404
        }

        return dtoConverter.convertToDto(byPostId.get());
    }

    public List<CommentDto> getCommentByRootPostId(Long rootPostId) {
        List<Comment> byRootPostId = commentRepository.findByRootPostId(rootPostId);
        if (byRootPostId == null || byRootPostId.isEmpty()) {
            return null; // TODO return 404
        }

        return byRootPostId.stream()
                .map(commentDtoConverter::convertToDto)
                .collect(Collectors.toList());
    }
}
