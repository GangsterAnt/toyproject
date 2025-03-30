package com.example.board.repository;

import com.example.board.bo.CommentBo;
import com.example.board.bo.PostBo;
import com.example.board.model.Post;
import com.example.board.service.converter.comment.CommentEntityConverter;
import com.example.board.service.converter.post.PostEntityConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class RepositoryWrapper {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    private final CommentEntityConverter commentEntityConverter;
    private final PostEntityConverter postEntityConverter;

    public PostBo getPostById(Long id) {
        Optional<Post> postOptional = postRepository.findActivePostByRootPostId(id);
        if (postOptional.isEmpty()) {
            return null; //TODO return 404
        }

        Post post = postOptional.get();
        List<CommentBo> collect = getCommentListByPostId(id);

        return postEntityConverter.convertFromEntityWithComments(post, collect);
    }

    public List<CommentBo> getCommentListByPostId(Long id) {
        return Optional.ofNullable(commentRepository.findActiveCommentsByRootPostId(id))
                .stream()
                .flatMap(List::stream)
                .map(commentEntityConverter::convertFromEntity)
                .collect(Collectors.toList());
    }

    public boolean hidePost(Long id) {
        try {
            postRepository.hidePostById(id);
            return true;
        } catch (Exception e) {
            log.warn("Failed to hide post: {}", id, e);
            return false;
        }
    }

    public boolean unHidePost(Long id) {
        try {
            postRepository.unHidePostById(id);
            return true;
        } catch (Exception e) {
            log.warn("Failed to un-hide post: {}", id, e);
            return false;
        }
    }

    public boolean hideComment(Long id) {
        try {
            postRepository.hideCommentById(id);
            return true;
        } catch (Exception e) {
            log.warn("Failed to hide post: {}", id, e);
            return false;
        }
    }

    public boolean unHideComment(Long id) {
        try {
            postRepository.unHideCommentById(id);
            return true;
        } catch (Exception e) {
            log.warn("Failed to un-hide post: {}", id, e);
            return false;
        }
    }

    public boolean hardDeletePost(Long id) {
        try {
            postRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            log.warn("Failed to delete post: {}", id, e);
            return false;
        }
    }

    public boolean softDeletePost(Long id) {
        try {
            ZonedDateTime now = ZonedDateTime.now(ZoneOffset.UTC);
            Instant instant = now.toInstant();
            Timestamp deleteAt = Timestamp.from(instant);
            postRepository.softDeleteById(id, deleteAt);
            return true;
        } catch (Exception e) {
            log.warn("Failed to delete post: {}", id, e);
            return false;
        }
    }

//    public List<PostSummaryBo> getPostSummaryList() {
//        List<Post> allPost = postRepository.findAll();
//        return postSummaryBoConverter.convertToBoList(allPost);
//    }
}
