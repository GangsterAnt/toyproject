package com.example.board.repository;

import com.example.board.bo.CommentBo;
import com.example.board.bo.PostBo;
import com.example.board.model.Post;
import com.example.board.service.converter.comment.CommentEntityConverter;
import com.example.board.service.converter.post.PostEntityConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class RepositoryWrapper {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    private final CommentEntityConverter commentEntityConverter;
    private final PostEntityConverter postEntityConverter;
//    private final PostSummaryBoConverter postSummaryBoConverter;

    public PostBo getPostById(Long id) {
        Post post = postRepository.getReferenceById(id);
        if (post == null) {
            return null; //TODO return 404
        }

        List<CommentBo> collect = getCommentListByPostId(id);

        return postEntityConverter.convertFromEntityWithComments(post, collect);
    }

    public List<CommentBo> getCommentListByPostId(Long id) {
        return Optional.ofNullable(commentRepository.findByRootPostId(id))
                .stream()
                .flatMap(List::stream)
                .map(commentEntityConverter::convertFromEntity)
                .collect(Collectors.toList());
    }

//    public List<PostSummaryBo> getPostSummaryList() {
//        List<Post> allPost = postRepository.findAll();
//        return postSummaryBoConverter.convertToBoList(allPost);
//    }
}
