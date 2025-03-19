package com.example.board.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RepositoryWrapper {

//    private final CommentRepository commentRepository;
//    private final PostRepository postRepository;
//
//    private final CommentBoConverter commentBoConverter;
//    private final PostBoConverter postBoConverter;
//    private final PostSummaryBoConverter postSummaryBoConverter;
//
//    public PostBo getPostById(Long id) {
//        Post post = postRepository.getReferenceById(id);
//        if (post == null) {
//            return null; //TODO return 404
//        }
//
//        List<CommentBo> collect = getCommentListByPostId(id);
//
//        return postBoConverter.convertFromEntityWithComments(post, collect);
//    }
//
//    public List<CommentBo> getCommentListByPostId(Long id) {
//        return Optional.ofNullable(commentRepository.findByRootPostId(id))
//                .stream()
//                .flatMap(List::stream)
//                .map(commentBoConverter::convertFromEntity)
//                .collect(Collectors.toList());
//    }
//
//    public List<PostSummaryBo> getPostSummaryList() {
//        List<Post> allPost = postRepository.findAll();
//        return postSummaryBoConverter.convertToBoList(allPost);
//    }
}
